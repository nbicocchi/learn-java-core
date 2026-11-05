# Lab 5 — Replace JDBC with a REST client

Read [theory.md](theory.md) first. The completed [address-book/](address-book/) project is an independent JavaFX frontend, not a backend or a duplicate of lab4.

## 1. Run the completed system first

Requirements: JDK 25, Maven, a graphical desktop for JavaFX, and the lab4 backend. PostgreSQL/Docker are needed **by the backend**, not by the desktop application's code. Maven needs network access on its first build to download dependencies.

Use the following commands from the **repository root**, not from a Maven source directory.

### Terminal 1 — Database and lab4 backend

If a previous lab's Compose copy is active, stop it there with `docker compose down` without deleting volumes. Then:

```bash
docker compose -f code/lab4/compose.yaml up -d --wait
docker compose -f code/lab4/compose.yaml exec -T postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 < code/lab4/sql/01-schema.sql
```

If you want the six sample contacts and have not already loaded them:

```bash
docker compose -f code/lab4/compose.yaml exec -T postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 < code/lab4/sql/02-seed.sql
```

Seeding is optional, not an application startup action. The schema creation is required if `persons` is absent: the lab2 name-import example's `names` table does not replace it. Existing contacts from labs 2–4 can be reused without resetting data. For PowerShell, the [lab4 instructions](../lab4/theory.md#run-the-example) explain the copy/`psql -f` alternative to input redirection.

Start the existing backend:

```bash
mvn -f code/lab4/address-book-backend/pom.xml spring-boot:run
```

### Terminal 2 — Check HTTP, then run JavaFX

```bash
curl -i http://localhost:8080/api/persons
mvn -f code/lab5/address-book/pom.xml clean test
mvn -f code/lab5/address-book/pom.xml javafx:run
```

Curl must return `200` and an array before you investigate GUI problems. An empty array is valid: use New to create a contact. There is **no backend, Compose file, or schema script inside lab5**.

The JavaFX application reads only one connection setting:

```bash
export ADDRESSBOOK_API_URL=http://localhost:8080
```

This is already the default. Set it in the frontend's shell before launching if the backend uses another host/port; in PowerShell use `$env:ADDRESSBOOK_API_URL = 'http://localhost:8080'`. Supply the **base URL**, not `/api/persons`: the client appends that path itself. No user information, query, or fragment belongs in the base URL.

If the backend uses another database port, configure its `ADDRESSBOOK_DB_URL` in **Terminal 1**, following lab4. Do not pass database credentials to the frontend. If the backend's HTTP port changes with `SERVER_PORT`, update `ADDRESSBOOK_API_URL` in Terminal 2 accordingly.

In IntelliJ, open `code/lab5/address-book/pom.xml` as the Maven project, select JDK 25, and set the API environment variable in the App run configuration if needed. Running App directly needs VM option `--enable-native-access=javafx.graphics`; `mvn javafx:run` already supplies it.

## 2. Make your own working copy of lab3

Keep the supplied lab3 and lab5 checkpoints unchanged as references. Copy the **lab3 JavaFX Maven project** into a separate working directory, excluding `target/` and IDE metadata. Do not copy lab3's Compose or SQL files: they belong to the database/backend side of this iteration.

The planned changes are:

| Keep | Replace or remove |
|---|---|
| Immutable local `Person` and its validation | JDBC repository/configuration and driver |
| Contact edit dialog and its controller | Database-specific startup dependencies |
| Table, detail rendering, selection, confirmation | Controller calls to repository with HTTP client calls |
| Background-task structure and busy state | Database wording with backend/HTTP wording |
| Model and GUI tests | JDBC integration tests with HTTP-client tests |

The interface looks almost the same. The architectural change should not force a redesign of the user's basic workflow.

## 3. Change the Maven and module dependencies

In your working POM:

1. Change the artifact ID to `lab5-address-book`.
2. Remove `org.postgresql:postgresql`.
3. Keep JavaFX, JUnit, and Java 25 compiler configuration.
4. Add `jackson-databind` and `jackson-datatype-jsr310` at version `2.20.1`.

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.20.1</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
    <version>2.20.1</version>
</dependency>
```

There is no HTTP client dependency to add: `HttpClient` comes with the JDK. Compare with the [complete POM](address-book/pom.xml). Its extra compiler/test settings for `jdk.httpserver` support only the local HTTP test server; they do not introduce a backend into the application.

Update `module-info.java` to the [completed descriptor](address-book/src/main/java/module-info.java):

```java
module com.nbicocchi.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    exports com.nbicocchi.addressbook;
    opens com.nbicocchi.addressbook.controller to javafx.fxml;
    opens com.nbicocchi.addressbook.client to com.fasterxml.jackson.databind;
}
```

Remove `java.sql` and `org.postgresql.jdbc`. The frontend does not need Spring Data annotations either; they belong to the backend model, not this local model.

## 4. Replace the persistence package with a client boundary

In your **working copy only**, remove the old `persistence` package (`DatabaseConfig`, `JdbcPersonRepository`, the JDBC repository interface, and data-access exception). Create `com.nbicocchi.addressbook.client`.

Add [PersonClient.java](address-book/src/main/java/com/nbicocchi/addressbook/client/PersonClient.java):

```java
public interface PersonClient {
    List<Person> findAll();
    Person findById(long id);
    Person create(Person draft);
    Person update(Person person);
    void deleteById(long id);
}
```

The controller depends on these application-level operations. It should not create HTTP requests itself. Unlike the old `save` method, create/update are separate so their different POST/PUT semantics stay explicit.

Add [RestClientException.java](address-book/src/main/java/com/nbicocchi/addressbook/client/RestClientException.java). It retains a received HTTP status for display/testing. A value of zero means there was no usable response, such as a refused connection or timeout. Do not translate these failures into an empty list.

## 5. Implement the reusable REST client

Create [RestPersonClient.java](address-book/src/main/java/com/nbicocchi/addressbook/client/RestPersonClient.java), using the reference implementation in the following order.

### 5.1 Configure transport and JSON

1. Validate the backend base URI and append `/api/persons`.
2. Create one `HttpClient` with a three-second connect timeout and no redirect following.
3. Set a ten-second request timeout on each `HttpRequest`.
4. Create one Jackson mapper with `JavaTimeModule` and ISO-string date serialization.

The client is reused, not rebuilt for every button click. Never add PostgreSQL settings here.

### 5.2 Keep input and response formats distinct

Copy the two small private records from the reference:

* `PersonInput`: six editable fields, **no ID**;
* `PersonResponse`: the same values plus the stored ID, with conversion to the local `Person`.

Do not call `mapper.writeValueAsString(person)` directly for POST/PUT. The model's `getId()` would add an input field that lab4 deliberately rejects. The result of POST is a new stored object; the draft remains unchanged.

### 5.3 Implement list and detail reads

For the list, send GET to the collection, require status `200` and a JSON content type, and deserialize `List<PersonResponse>` using a `TypeReference`. Convert every response item to a validated `Person`. `[]` is valid; JSON `null`, an HTML page, and objects without stored IDs are not valid contact lists.

For detail, send GET to `/api/persons/{id}` and deserialize one stored object. The GUI currently renders details from the loaded list, but this operation documents and tests the API's individual-resource contract too.

### 5.4 Implement writes

| Operation | Body and success handling |
|---|---|
| Create | POST six fields, require `201`, decode returned stored contact. |
| Update | PUT six fields to the ID path, require `200`, decode returned contact. |
| Delete | DELETE the ID path, require `204`, **do not parse a JSON body**. |

Use UTF-8 request publishers and response handlers so accented names survive the round trip. Keep the postal code as a string and the birthday in ISO format.

### 5.5 Handle errors before parsing contacts

The shared `send` method checks the operation's expected status before deserialization. An error response may contain Problem Details JSON rather than a contact; extract its `detail` if present and retain a useful fallback when the body is empty or HTML.

Wrap `IOException` as a connection/timeout error. If interrupted, restore the interrupt flag before throwing the client exception. Invalid success JSON is a separate protocol error. Do not add an automatic POST retry: uncertainty does not mean the write was rolled back.

## 6. Adapt the overview controller, not the interface

Compare [PersonOverviewController.java](address-book/src/main/java/com/nbicocchi/addressbook/controller/PersonOverviewController.java) with lab3:

1. Replace `PersonRepository repository` with `PersonClient client` and update the `configure` parameter.
2. Rename `runDatabaseAction` to `runRemoteAction`.
3. New calls `client.create(person)`; Edit calls `client.update(person)`; Delete calls `client.deleteById(id)`.
4. Reload and post-write refresh call `client.findAll()`.
5. Change status/error text to refer to the backend. Display the HTTP problem detail when available.
6. Keep the `Task<List<Person>>` worker, content disabling, and success/failure callbacks.

**Do not call `HttpClient.send` on the JavaFX thread.** Capture form data on that thread, execute network work on the worker, and change controls only from the JavaFX callbacks. A mutation followed by list reload is two separate HTTP requests: the mutation can succeed even if the reload fails. Retain the warning to inspect/reload before retrying a write.

In the overview FXML, change only the reload button's text to **Reload from backend**. Preserve its ID, action method, and the rest of the layout. The edit FXML, edit controller, styling, local model, cancellation behavior, and delete confirmation can remain unchanged.

## 7. Adapt startup and shutdown

Update [App.java](address-book/src/main/java/com/nbicocchi/addressbook/App.java):

1. Create the REST client from `ADDRESSBOOK_API_URL`, defaulting to `http://localhost:8080`.
2. Load/show the existing view and pass that client plus the single-worker executor to `configure`.
3. Rename the worker and window title to identify lab5/HTTP rather than PostgreSQL.
4. On `stop()`, request worker interruption, then close the REST client. Its close implementation calls `HttpClient.shutdownNow()` rather than blocking the UI while waiting for requests to drain.

Do not copy a Spring Boot main class or start the backend as a child of JavaFX. The backend is the separate lab4 process from step 1.

## 8. Replace database tests with HTTP tests

Remove the JDBC repository integration tests from your working frontend. Keep the model tests and adapt the GUI test double to implement `PersonClient` instead of `PersonRepository`.

The supplied [RestPersonClientTest](address-book/src/test/java/com/nbicocchi/addressbook/client/RestPersonClientTest.java) runs a small HTTP test server on a dynamically assigned loopback port. This is a **test fixture**, not a copy of lab4. It records actual HTTP requests and returns controlled responses, checking:

* GET/POST/PUT/DELETE paths, headers, and JSON;
* ISO dates, UTF-8 names, and leading-zero postal codes;
* no `id` in create/update bodies;
* empty arrays and empty DELETE responses;
* 400/404/503 problems, HTML errors, and invalid success representations;
* no response retry after a failed POST, connection refusal, timeouts, and interruption.

From the repository root:

```bash
mvn -f code/lab5/address-book/pom.xml test
mvn -f code/lab5/address-book/pom.xml -Djavafx.smoke=true test
```

The default command runs **13 tests** (five model and eight HTTP-client tests) and skips two GUI tests. No Docker, PostgreSQL, lab4 process, or display is needed; the HTTP tests need permission to bind a loopback port. The GUI switch adds two tests and requires a graphical session, but still uses a test double rather than the live backend. With both categories active, all **15 tests** should pass. On a headless Linux runner with Xvfb, prefix the GUI test command with `xvfb-run -a`.

For your separate working project, omit `-f code/lab5/address-book/pom.xml` and run from its POM directory.

## 9. Verify the real frontend/backend flow

With PostgreSQL and lab4 running, launch lab5 and check:

1. Contacts match `curl http://localhost:8080/api/persons`.
2. Create a contact with a leading-zero postal code and an accented or apostrophe-containing name; it survives Reload and frontend restart.
3. Edit a sorted row and confirm that the same stored ID changes; cancellation leaves data unchanged.
4. Confirm one deletion and cancel another. Check the backend with curl.
5. Restart the backend, then Reload: the same PostgreSQL data should reappear without restarting JavaFX.
6. Stop the backend while JavaFX remains open, then Reload. The window should remain movable/repaintable and later show a connection error; it must not replace contacts with fake samples.
7. Restart lab4, dismiss the error, and Reload to recover.
8. With the backend up, stop only PostgreSQL. Reload now produces an **HTTP 503** from the backend, distinguishable from connection refused. Restart PostgreSQL and recover through Reload.
9. Delete a selected contact through curl while JavaFX holds an old snapshot, then try to edit it. Expect a 404 message, not a silent insertion. Reload to remove the stale row.

Use a practice contact's actual ID for curl checks; do not modify unrelated contacts. Concurrent-edit conflict resolution is not implemented in either lab4 or lab5.

## 10. Stop the system

Close the JavaFX window, stop the backend with Ctrl+C in its own terminal, and stop the course database when no longer needed:

```bash
docker compose -f code/lab4/compose.yaml down
```

Do not add `--volumes`: later iterations can continue using the same data.

## Troubleshooting

| Symptom | What to check |
|---|---|
| Cannot reach backend / timeout | Lab4 process, HTTP host/port, and `ADDRESSBOOK_API_URL`; PostgreSQL readiness alone is not enough. |
| HTTP 503 | The backend answered but its data operation failed. Check lab4 logs, database settings, and `persons` schema. |
| HTTP 400 on create/update | All six fields, ISO date, string postal code, and especially the absence of `id` in the input JSON. |
| HTTP 404 after editing | The contact may have been deleted by another client. Reload; do not turn an update into a new POST. |
| Unexpected representation / HTML response | Base URL may point at another service or include `/api/persons` twice. |
| Missing JavaFX runtime / native warning in IDE | Import the Maven project and use the module-aware launch; add `--enable-native-access=javafx.graphics` to direct IDE VM options. |
| Jackson reflection access error | Check the client package's qualified `opens` declaration and keep the Jackson dependencies/modules aligned. |
| Frozen UI | Ensure all HTTP work stays inside the background task, never in a button handler or FXML initialization. |
