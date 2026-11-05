# Lab 3 — From SQL to a persistent application

## The next iteration

In lab2 we manipulated PostgreSQL directly with `psql`. Now we implement the same operations in Java and connect them to the address book. The visible change is small—contacts can be created, edited, and deleted—but the database becomes the source of truth.

```text
FXML view ↔ JavaFX controller → PersonRepository
                                    ↓
                           JdbcPersonRepository
                                    ↓
                        JDBC driver → PostgreSQL
```

The controller knows which operation the user requested. The repository knows SQL and how rows become objects. The model knows contact values. None of the FXML files contains a database query.

## Identity is no longer a table position

A contact now has a nullable `Long id`:

* **null:** a new draft not yet stored;
* **positive value:** the database-generated identity of a stored row.

Sorting changes row positions, not database identity. Edit/delete must use the selected object's ID, not its index in the table. Replacing a person's address or name must retain the same ID.

`Person` remains immutable. The edit form creates a new value with the original ID; it does not modify the selected object as the user types. This gives cancellation a simple, dependable meaning: no new value is saved and the old one stays unchanged.

## A small repository contract

```java
public interface PersonRepository {
    List<Person> findAll();
    Optional<Person> findById(long id);
    Person save(Person person);
    boolean deleteById(long id);
}
```

* `findAll` returns contacts in a deterministic name/ID order.
* `findById` returns an empty optional when a row is absent, not when the database is unavailable.
* `save` inserts a draft or updates an existing ID, returning the stored contact. An update of a missing ID is an error, **not** a silent insertion.
* `deleteById` reports whether a row was removed. Deleting an already-absent row is harmless in this UI.

We use a contact-specific interface rather than a generic repository hierarchy. Its purpose is separating responsibilities, not creating layers for their own sake. The same interface also lets GUI tests use a small test double without a live database.

## JDBC implementation

`DatabaseConfig` reads environment settings and opens a connection. The PostgreSQL driver is a Maven dependency; `module-info.java` adds `requires java.sql` and `requires org.postgresql.jdbc`.

Each repository operation obtains and closes its own connection, statement, and result set with try-with-resources. A result set never escapes the method. A connection pool is deliberately postponed.

### Mapping rows

```java
new Person(row.getLong("id"), row.getString("first_name"), row.getString("last_name"),
        row.getString("street"), row.getString("postal_code"), row.getString("city"),
        row.getObject("birthday", LocalDate.class));
```

PostgreSQL's JDBC driver supports `LocalDate` for SQL `DATE`. Our schema rejects null birthdays, so the model and mapping share that contract.

### Binding values

```java
String sql = "DELETE FROM persons WHERE id = ?";
try (Connection connection = database.openConnection();
     PreparedStatement statement = connection.prepareStatement(sql)) {
    statement.setLong(1, id);
    return statement.executeUpdate() == 1;
}
```

The parameter is not interpolated into the SQL string. A contact named `D'Angelo` works without custom escaping. The integration test includes apostrophes to check this behavior.

### Insert and update

PostgreSQL's `RETURNING` clause returns the generated ID and stored fields from a write. Consequently, the repository uses `executeQuery()` for insert/update statements with `RETURNING`, and `executeUpdate()` for delete.

The SQL statements are fixed, except for composition of trusted column constants; user input is always bound. We do not issue a “does this ID exist?” query before every update: the update's returned rows tell us whether it matched.

The repository does not create or reset the schema. That responsibility remains in the explicit SQL scripts from lab2. A SQL error must never cause a destructive table reset.

## Validation at two boundaries

The edit form requires all six values. It strips leading/trailing text whitespace and reports missing or oversized text in an inline message; the birthday must be selected with the calendar. The model enforces text lengths and nonblank values too. The database independently enforces column lengths, required values, and nonblank text.

Do not parse postal codes as integers. We do not impose one country's exact postal format or attempt complete real-world address validation.

On **OK**, the form validates before it is allowed to close. On **Cancel** or window close, no repository method is called. A delete has a separate confirmation prompt.

## Keeping the interface responsive

Database operations can block on network I/O, locks, or an unavailable server. Running them inside a button handler would freeze the JavaFX Application Thread.

The completed project supplies a small helper around `Task<List<Person>>` and a single-worker executor:

1. On the UI thread, capture an immutable contact value or selected ID.
2. Disable the content controls and display a progress message.
3. On the worker, perform the repository mutation, then query the current list.
4. In `setOnSucceeded`, back on the UI thread, replace the list and clear stale selection.
5. In `setOnFailed`, retain the displayed data, show an error, and re-enable controls for retry.

Treat this helper as provided infrastructure if the lecture on executors has not happened yet. The important rule now is **no SQL on the UI thread and no UI updates from the worker**. E8 provides the fuller concurrency explanation.

Only one operation is in flight, avoiding double-click submission and conflicting local tasks. Driver connection/socket timeouts bound ordinary network waits. `App.stop()` requests worker shutdown; the worker thread is a daemon so it cannot keep the application alive after its window closes. This is not a complete production cancellation or retry policy.

## Failure does not always mean “nothing happened”

Our writes are single SQL statements in auto-commit mode. The subsequent list refresh runs on another connection. If the write commits but the refresh fails, the UI reports an error even though data may have changed. Reload before retrying a write; otherwise a repeated insert could create another contact.

Likewise, a lost connection near commit can make a write's outcome uncertain. The error message does not claim a rollback. More advanced retry/idempotency and multi-operation transaction designs are outside this lab.

`DataAccessException` preserves the original `SQLException` as its cause. The UI shows a comprehensible message while terminal output retains diagnostics. An unavailable server is never replaced silently with the sample list.

## Testing responsibilities

* **Model tests:** Java-only validation and ID semantics.
* **Repository integration tests:** real PostgreSQL, isolated temporary schema, insert/read/update/delete, generated IDs, apostrophes, and failure causes.
* **GUI smoke tests:** real FXML, selection, form validation, and save wiring against a repository test double.
* **Manual acceptance:** actual UI plus actual database, persistence across restarts, cancellation, deletion, and failure/recovery.

Tests do not prove every interaction works. Their separation helps identify whether a failure belongs to Java validation, SQL mapping, or GUI wiring.

## Before the backend/frontend split

At the end of lab3, **the desktop application still connects directly to PostgreSQL**. There is no server-side Java process yet. In later labs, the backend will own JDBC access, and the desktop client will use HTTP instead. Keeping SQL out of the controller makes that change easier to explain.

## References

* [Instructor's JDBC address book](https://github.com/nbicocchi/learn-java-javafx/tree/main/code/jdbc-address-book)
* [JDBC theory source](https://github.com/nbicocchi/learn-java-javafx/blob/main/slides/E3.1%20-%20JDBC.md)
* [PostgreSQL JDBC documentation](https://jdbc.postgresql.org/documentation/)
* [JavaFX Task API](https://openjfx.io/javadoc/25/javafx.graphics/javafx/concurrent/Task.html)
