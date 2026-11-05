# Lab 3 — Connect the address book to PostgreSQL

## Prerequisites and scope

Requires JDK 25, Maven, a graphical desktop, and Docker with Compose V2. This lab adds a JDBC repository, persistent CRUD, form validation, cancellation, delete confirmation, and supplied background-task infrastructure. It does not introduce REST, Spring Boot, ORM/JPA, connection pools, authentication, or concurrent-edit conflict resolution.

The completed [Maven project](address-book/) runs independently of the other Java projects. Database preparation is described in section 2. After that, from `code/lab3/`, run `mvn -f address-book/pom.xml clean test` and `mvn -f address-book/pom.xml javafx:run`. No fake contacts are substituted when the database fails.

## 1. Start from the preceding checkpoint

Keep the completed [`address-book/`](address-book/) directory as reference. For your own work, copy the **[lab1 Maven project](../lab1/address-book/)** to a separate working directory, without `target/` or IDE-generated files. Do not modify the lab1 reference in place. Lab2 supplies the PostgreSQL environment, SQL scripts, and small JDBC console examples, not another GUI project.

The tutorial first verifies the database, then builds the repository, then connects the UI. Read [theory.md](theory.md) first; its description of background tasks is also the contract for the supplied concurrency helper.

## 2. Reuse or create the database

**Required before launching the application:** PostgreSQL must contain the `persons` table. The lab2 JDBC micro-examples create only a separate `names` table; running those examples does not prepare `persons`. Run `sql/01-schema.sql` below even if you have already imported names. It safely leaves an existing `persons` table and its data intact.

If lab2's database is running, first run `docker compose down` from `code/lab2/`, without deleting volumes. Then move to `code/lab3/` and run:

```bash
docker compose up -d --wait
docker compose exec -T postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 < sql/01-schema.sql
docker compose exec postgres psql -U addressbook -d addressbook -c "SELECT id, first_name, last_name FROM persons ORDER BY id;"
```

The Compose project/volume names match lab2, so existing data is reused. These files also work if you skipped the earlier lab and start with an empty database.

If the application reports `relation "persons" does not exist`, check that the schema command completed successfully and that `ADDRESSBOOK_DB_URL` points to this same database and published host port. Starting the container or the Java application alone does not create the table.

Optionally seed sample contacts on a fresh database:

```bash
docker compose exec -T postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 < sql/02-seed.sql
```

For PowerShell, avoid input redirection:

```bash
docker compose cp sql/01-schema.sql postgres:/tmp/01-schema.sql
docker compose cp sql/02-seed.sql postgres:/tmp/02-seed.sql
docker compose exec postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 -f /tmp/01-schema.sql
docker compose exec postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 -f /tmp/02-seed.sql
```

Do not rerun the seed each time the application starts: it can restore sample contacts you intentionally deleted. The application itself performs **no automatic schema creation, reset, or seeding**.

## 3. Configure the Maven project

In your working project, update the artifact ID to `lab3-address-book`, then add the PostgreSQL driver dependency:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.5</version>
</dependency>
```

Keep JDK 25, JavaFX 25.0.1, JUnit, and the native-access JVM option from lab1. In `module-info.java`, add:

```java
requires java.sql;
requires org.postgresql.jdbc;
```

See the completed [POM](address-book/pom.xml) and [descriptor](address-book/src/main/java/module-info.java) if your configuration differs. Reload Maven in the IDE.

Create package `com.nbicocchi.addressbook.persistence`. All source links below point to the completed project, not dependencies on another lab at runtime.

## 4. Give contacts a stable database identity

Update [`Person.java`](address-book/src/main/java/com/nbicocchi/addressbook/model/Person.java):

1. Add `private final Long id` and a getter.
2. Keep the original constructor for a new draft, delegating to a second constructor with a null ID.
3. Add a constructor accepting an ID plus the six existing fields. A non-null ID must be positive.
4. Require nonblank text with lengths matching the database: name fields 80, street 160, postal code 20, city 100.
5. Keep the birthday as a non-null `LocalDate` and the postal code as a `String`.

Do not add setters. An edited contact will be a **new object with the same ID**; this avoids altering displayed data before the user confirms the form. The model's length check counts Unicode code points, matching PostgreSQL character lengths more closely than Java's UTF-16 `length()`.

Remove `SampleContacts.java` from `src/main/java`: startup must no longer manufacture contacts. The supplied tests include their own fixture helper under `src/test/java`; it is not part of the running application.

## 5. Implement connection configuration

Create [`DatabaseConfig`](address-book/src/main/java/com/nbicocchi/addressbook/persistence/DatabaseConfig.java). Read its `fromEnvironment()` and `openConnection()` methods.

Defaults match the local Compose service. The application reads these optional environment variables:

| Variable | Default |
|---|---|
| `ADDRESSBOOK_DB_URL` | `jdbc:postgresql://localhost:5432/addressbook` |
| `ADDRESSBOOK_DB_USER` | `addressbook` |
| `ADDRESSBOOK_DB_PASSWORD` | `local-course-password` |

The driver receives username/password as properties, plus connection and socket timeouts. No password is included in the source URL. These local defaults are only for the course exercise; do not commit real deployment credentials.

If you chose host port `55432` in lab2, configure **both sides** in the shell from which you start Compose/Maven:

```bash
export POSTGRES_PORT=55432
export ADDRESSBOOK_DB_URL=jdbc:postgresql://localhost:55432/addressbook
```

PowerShell equivalents are `$env:POSTGRES_PORT = '55432'` and `$env:ADDRESSBOOK_DB_URL = 'jdbc:postgresql://localhost:55432/addressbook'`. In IntelliJ, set the JDBC environment variables in the App run configuration; shell settings do not automatically propagate to an already-open IDE.

## 6. Implement the repository, one operation at a time

Create [`PersonRepository`](address-book/src/main/java/com/nbicocchi/addressbook/persistence/PersonRepository.java) and [`DataAccessException`](address-book/src/main/java/com/nbicocchi/addressbook/persistence/DataAccessException.java). Then create [`JdbcPersonRepository`](address-book/src/main/java/com/nbicocchi/addressbook/persistence/JdbcPersonRepository.java) in this order:

### 6.1 Read and map

1. Store a `DatabaseConfig` passed to the constructor.
2. Define the selected column names once.
3. Implement `map(ResultSet)` using column names and `getObject("birthday", LocalDate.class)`.
4. Implement `findAll()` with `ORDER BY last_name, first_name, id`.
5. Implement `findById(long id)` with `WHERE id = ?` and `setLong(1, id)`.

Wrap the connection, statement, and result set in try-with-resources. Return Java values, not JDBC resources. Preserve `SQLException` as the cause of `DataAccessException`; do not return an empty list on failure.

### 6.2 Save

Implement `save(Person person)` with two fixed SQL statements:

* Null ID: `INSERT ... VALUES (?, ?, ?, ?, ?, ?) RETURNING ...`.
* Existing ID: `UPDATE ... SET ... WHERE id = ? RETURNING ...`.

Bind all six values; bind the ID as parameter 7 only for an update. Map and return the row returned by PostgreSQL. If an update returns no row, report that the contact no longer exists rather than inserting a different row.

### 6.3 Delete

Implement `DELETE FROM persons WHERE id = ?`. Return whether `executeUpdate()` affected one row. Never delete by a table-view row index or by the person's name.

**Checkpoint:** compare your SQL with the commands from lab2. The main differences are placeholders, parameter binding, and mapping to Java objects.

## 7. Verify JDBC before modifying the GUI

Copy the reference [`src/test/`](address-book/src/test/) into your working project. Until the new FXML/controllers are ready, leave GUI smoke tests disabled, as they are by default.

From your working Maven project directory:

```bash
mvn clean test
mvn -Djdbc.integration=true test
```

The first command needs no database. The second adds the real PostgreSQL repository tests. They create a randomly named temporary test schema, run CRUD with an apostrophe-containing name and leading-zero postal code, then remove **only that test schema**. They do not clear your contacts. The configured user must be allowed to create schemas; use the local course database, not a production server.

The test also verifies that updating a missing ID fails and that connection failures retain their SQL cause. A green GUI test alone would not prove these JDBC behaviors.

## 8. Build the edit dialog in Scene Builder

Create `person-edit-view.fxml` alongside the overview FXML. Use the completed [dialog FXML](address-book/src/main/resources/com/nbicocchi/addressbook/person-edit-view.fxml) as reference:

1. Root: `DialogPane`, preferred width `480`.
2. Content: a VBox with padding `20` and spacing `16`.
3. Add a heading and a two-column, six-row GridPane with gaps `16` and `12`.
4. Add labels and five `TextField` controls for first name, last name, street, postal code, and city.
5. Add a `DatePicker` for birthday. Set **Editable = false** so dates are selected through the calendar rather than partially typed locale-dependent text.
6. Add a wrapping error label below the grid.
7. Add built-in **OK** and **Cancel** button types to the DialogPane.
8. Reuse `@styles/address-book.css`; add `.error { -fx-text-fill: #a51d2d; }` to the stylesheet.

Create [`PersonEditDialogController`](address-book/src/main/java/com/nbicocchi/addressbook/controller/PersonEditDialogController.java). Link the controller class and exact IDs:

```text
firstNameField, lastNameField, streetField, postalCodeField, cityField,
birthdayPicker, errorLabel
```

`setPerson` fills the form or clears it for a new draft. `readPerson` constructs a new validated `Person`, preserving the old ID. It must not mutate the selected contact. `showValidationError` writes a message inside the dialog.

## 9. Add CRUD controls to the overview

In Scene Builder, open the overview:

1. Give the SplitPane `fx:id="contentPane"`; the controller disables this content during a database task.
2. Replace the sample reload button with `Reload from database`, ID `reloadButton`, action `#handleReload`.
3. Above it, add an HBox with spacing `8` and three buttons:

| Text | `fx:id` | On Action |
|---|---|---|
| `New…` | `newButton` | `#handleNew` |
| `Edit…` | `editButton` | `#handleEdit` |
| `Delete` | `deleteButton` | `#handleDelete` |

Keep the existing table, details, selection hint, and clear-selection button. Compare with the completed [overview FXML](address-book/src/main/resources/com/nbicocchi/addressbook/person-overview-view.fxml). The JavaFX list starts empty now: **do not reload sample data on startup**.

## 10. Wire dialogs and database tasks

Use the completed [`PersonOverviewController`](address-book/src/main/java/com/nbicocchi/addressbook/controller/PersonOverviewController.java) as the implementation reference. Keep column setup and selection rendering from lab1, then make these changes:

1. Add the content pane and edit/delete button fields. Disable edit/delete when there is no selection.
2. Add `configure(PersonRepository, Executor)`: dependencies are supplied after FXML loading; injected fields already exist at that point.
3. Add `editPerson(Person)` to load the form, initialize its controller, set the owner window, and show a `Dialog<Person>`.
4. Install an action filter on the dialog's OK button. If validation fails, show the error and consume the event so the dialog remains open.
5. Set a result converter: OK produces the new contact value, while Cancel/close produces no result.
6. New/Edit pass the returned value to `repository.save`; Delete first asks for confirmation, then uses the selected ID.

Copy the provided `runDatabaseAction`/`finishAction` helper and read its callbacks. The worker performs the write and reload; UI callbacks replace the list or report failure and re-enable controls. Do not move SQL back into button handlers. This helper is supplied infrastructure until the concurrency lectures.

No code should change the displayed list optimistically before the database succeeds. However, a write can commit before a subsequent refresh fails: the error message tells the user to **reload before retrying** rather than falsely promising that nothing changed.

## 11. Update application startup and shutdown

Update [`App.java`](address-book/src/main/java/com/nbicocchi/addressbook/App.java):

1. Own one single-worker daemon executor for database operations.
2. Load FXML, show the stage, then get its controller from the same `FXMLLoader`.
3. Supply `new JdbcPersonRepository(DatabaseConfig.fromEnvironment())` and the executor through `configure`.
4. In `stop()`, request executor shutdown with `shutdownNow()`.

Connections are short-lived repository resources; there is no connection pool to close. The worker owns no JavaFX controls. Keep the Maven native-access JVM option; when running App directly in IntelliJ add `--enable-native-access=javafx.graphics` in VM options.

Run the application from your working project's directory:

```bash
mvn javafx:run
```

Or run the supplied checkpoint from `code/lab3/` with `mvn -f address-book/pom.xml javafx:run`.

## 12. Acceptance checks with the real database

1. Confirm the table contains PostgreSQL contacts, including any changes you made in lab2.
2. Create a new contact, including a postal code with a leading zero. Confirm it appears in the list and query it through `psql`.
3. Open New, leave fields incomplete, press OK: an inline error appears and the form stays open.
4. Open Edit, change fields, then Cancel: neither displayed values nor database rows change.
5. Edit and confirm: the contact's ID stays the same and the stored values change.
6. Sort the table, then edit/delete a selected row: the correct database ID must be affected.
7. Cancel a delete confirmation, then confirm another: only the confirmed deletion changes the database.
8. Close/restart the application: changes remain, and deleted contacts do not reappear unless you explicitly rerun the seed script.
9. Stop PostgreSQL with `docker compose stop postgres`. Click Reload: the window must remain responsive while waiting, then display an error. Existing displayed values remain, without being replaced by fake samples.
10. Restart with `docker compose up -d --wait`, dismiss the error, and Reload: data should load again.

During an operation the content controls are disabled to prevent repeated submissions; window movement and repainting remain responsive. This is a deliberately simple busy-state policy.

## 13. Automated checks and shutdown

From your working Maven project directory:

```bash
mvn test
mvn -Djdbc.integration=true test
mvn -Djavafx.smoke=true test
mvn -Djavafx.smoke=true -Djdbc.integration=true test
```

The default command runs five model/data tests and skips four opt-in tests; it requires neither PostgreSQL nor a display. `jdbc.integration` adds two tests against real PostgreSQL using a randomly named temporary schema that is removed afterward, without clearing `public.persons`. The database user must be allowed to create/drop schemas; do not use a production database. `javafx.smoke` adds two GUI tests using an in-memory repository test double, so it requires a display but not PostgreSQL.

For the supplied checkpoint, run these commands from `code/lab3/` with `-f address-book/pom.xml`, for example `mvn -f address-book/pom.xml -Djdbc.integration=true test`.

The GUI switch needs a display; on a headless Linux runner with Xvfb, prefix the command with `xvfb-run -a`. GUI tests use a test double of the repository, while JDBC tests use real PostgreSQL. With both enabled, expect nine tests and no skips.

Stop the course database from `code/lab3/`:

```bash
docker compose down
```

Do not add `--volumes`: that would delete the course database. The next labs will move JDBC into a backend rather than replace these data with an unrelated example.
