# Lab 4 — A first REST backend with Spring Boot

## What is a backend?

In lab3, the JavaFX desktop application owns everything: screens, event handlers, JDBC access, and database credentials. PostgreSQL is already a separate server, but there is no Java application server between it and the desktop.

Now we introduce that missing component: a **backend**. It is a Java process that receives requests, applies rules, accesses data, and returns responses. It has no graphical window.

```text
Lab 3:
JavaFX application → JDBC → PostgreSQL

Lab 4 demonstration:
curl → HTTP/JSON → Spring Boot backend → Spring Data JDBC → PostgreSQL

Next iteration:
JavaFX frontend → HTTP/JSON → Spring Boot backend → Spring Data JDBC → PostgreSQL
```

We keep the contacts table and the CRUD operations. What changes is **who owns database access and how another program asks for it**. The desktop application from lab3 is not silently changed by running lab4: it will become an HTTP client in lab5.

## Why put a Java server in the middle?

Direct database access makes a small desktop demonstration easy, but every distributed client needs database connectivity and credentials. A backend gives clients a narrower application-level contract:

* The client requests “create a contact,” not “execute arbitrary SQL.”
* Validation and data-access rules can live centrally.
* The database credentials stay on the backend machine.
* Multiple clients can use the same API: desktop, mobile, command-line, or browser.

This adds costs too: another process to run, network failures, timeouts, and an API that must remain compatible with its clients. A server is not automatically the right architecture for every small program.

## HTTP: a request and a response

HTTP is an application protocol used to exchange requests and responses. Although browsers are familiar HTTP clients, any program can use it. In this lab, `curl` is the simplest client.

A request includes:

* a **method**, describing the requested operation;
* a **path**, identifying the resource;
* **headers**, carrying metadata such as the body's content type;
* an optional **body**, containing the input data.

A response includes a numeric **status**, headers, and optionally a body. For example:

```http
GET /api/persons/42 HTTP/1.1
Host: localhost:8080
Accept: application/json
```

```http
HTTP/1.1 200 OK
Content-Type: application/json

{"id":42,"firstName":"Anna","lastName":"Rossi","street":"Via Emilia 12","postalCode":"41121","city":"Modena","birthday":"1998-03-14"}
```

These illustrate the logical HTTP messages, not all transport-level details or headers. `localhost` is the machine running the client. `8080` is our backend's HTTP port; `5432` is PostgreSQL's database port. They serve different protocols and are not interchangeable.

## Resources and REST-style operations

An API—Application Programming Interface—is a contract another program can use. A REST-style HTTP API describes operations on resources using paths, HTTP methods, representations, and response semantics.

Here the resources are **contacts**, represented under `/api/persons`. We do not invent an endpoint such as `/executeSql` or one URL for every button. The protocol is independent of our future UI.

| Method | Path | Meaning | Success |
|---|---|---|---|
| GET | `/api/persons` | List contacts | 200 with an array |
| GET | `/api/persons/{id}` | Read one contact | 200 with an object |
| POST | `/api/persons` | Create a contact | 201 with the new object and Location |
| PUT | `/api/persons/{id}` | Replace editable fields of an existing contact | 200 with the updated object |
| DELETE | `/api/persons/{id}` | Remove a contact | 204 with no body |

GET should not change application data. PUT and DELETE are **idempotent in their intended effect**: repeating the same request should not create additional changes beyond that effect. This does not require identical response statuses: after a successful delete, another delete can return 404.

POST is not idempotent here: sending the same contact twice creates two rows with different IDs. Names do not uniquely identify people. Our PUT does not create a missing row; it returns 404. There is no PATCH endpoint for partial updates in this example.

An API is not “REST” merely because it returns JSON. This is a small REST-style introduction, not a complete treatment of REST's architectural constraints.

## JSON is data, not a Java object

JSON represents structured values as text. It supports objects, arrays, strings, numbers, booleans, and null, but not Java-specific object references or a built-in date type.

```json
{
  "firstName": "Ada",
  "lastName": "Lovelace",
  "street": "Via REST 1",
  "postalCode": "00118",
  "city": "Roma",
  "birthday": "1995-12-10"
}
```

This is a complete request body for POST or PUT. All six fields are required. The postal code is a string, preserving leading zeros; the birthday is a string using the API's ISO date convention. The ID is absent: POST generates it, while PUT uses the URL's ID. Sending an `id` or another unknown property in the body is rejected by this example's strict JSON configuration.

The client and backend do not share the same Java object in memory. The backend **deserializes** JSON into Java values and **serializes** the result back into JSON. Spring's JSON integration uses Jackson; it also supports the conversion between `LocalDate` and the chosen date string.

HTTP's `Content-Type: application/json` identifies the request body format. Returning Java's `toString()` would not be equivalent to returning valid JSON.

## What Spring and Spring Boot do

**Spring Framework** provides infrastructure for constructing application components, handling web requests, and more. **Spring Boot** supplies convenient startup, dependency management, and auto-configuration around Spring.

Our application starts through:

```java
@SpringBootApplication
public class AddressBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }
}
```

This discovers application components under `com.nbicocchi.addressbook`, configures them, and starts an embedded HTTP server. No external Tomcat installation or IDE application-server configuration is required.

The example pins **Spring Boot 3.5.16**, whose documented system requirements support Java 25. It is a chosen teaching baseline, not a claim that 3.x is the newest Spring Boot generation. The Boot parent manages compatible dependency/plugin versions; the project sets `java.version` to `25`.

The relevant Maven dependencies are:

* `spring-boot-starter-web`: HTTP routing, embedded web server, and JSON support;
* `spring-boot-starter-data-jdbc`: repository generation, relational mapping, and JDBC datasource infrastructure;
* the PostgreSQL driver: speaks the database protocol;
* `spring-boot-starter-test`: testing support, only for tests.

Spring Data JDBC is **not JPA/Hibernate**. It still communicates through JDBC, but generates ordinary SQL and maps rows from our model metadata. It has no JPA persistence context or lazy-loading proxies for related objects. For our one-table example, the important change is that we no longer write the CRUD implementation ourselves.

## Increasing levels of automation

The course now makes three levels visible:

| Lab | What we write | What a library/framework does |
|---|---|---|
| 2 | SQL, parameters, result-set iteration in small Java programs | JDBC driver handles the database protocol. |
| 3 | A complete JDBC repository and its row mapping | JavaFX supplies the UI/event infrastructure. |
| 4 | A repository **interface**, contact mapping, and HTTP handlers | Spring Data generates the repository behavior; Spring handles HTTP and component wiring. |

Here is the complete production repository declaration:

```java
public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findAllByOrderByLastNameAscFirstNameAscIdAsc();
}
```

`Person` is the stored type and `Long` is its ID type. `CrudRepository` already declares `save`, `findById`, `findAll`, `existsById`, `deleteById`, and other basic operations. **There is no `PersonRepositoryImpl` to write.** At startup, Spring Data creates a repository object implementing this interface.

The extra method uses a naming convention: return all contacts ordered by last name, first name, and ID. Spring Data derives the query from those property names. We do not write that SELECT, but we still need to understand its ordering and database cost.

The model supplies the mapping metadata:

```java
@Table("persons")
public final class Person {
    @Id
    private final Long id;
    @Column("first_name")
    private final String firstName;
    // Other fields, constructor, getters, and withId(Long) in the example.
}
```

These annotations come from **Spring Data**, not `jakarta.persistence`. `@Table` selects the existing table, `@Id` identifies the key, and `@Column` handles names such as `first_name`. Simple fields use Spring Data JDBC's naming convention when no explicit column name is supplied.

A null ID marks a new contact. `save` inserts it and returns a stored contact with its generated ID; with a non-null ID, it performs an update. The immutable model supplies `withId(Long)` so the framework can return a new value after ID generation. **Always use the return value of `save`** rather than expecting it to mutate the original input object.

Mapping metadata does not create or migrate the database table. Automation moves responsibilities into a framework; it does not remove the need to understand SQL, constraints, identity, and failure behavior. The previous labs give us the tools to investigate what happens underneath.

## Dependency injection without magic

In earlier labs, `App` explicitly constructed a repository and passed it to a controller. Spring does similar wiring for components it manages:

```java
public PersonController(PersonRepository repository) {
    this.repository = repository;
}
```

The controller declares what it needs through its constructor. Spring supplies the implementation **generated by Spring Data JDBC**. The framework uses the `DataSource` configured from `application.properties`.

```text
PersonController needs PersonRepository
    → Spring Data creates the repository implementation
        → JDBC infrastructure uses the configured DataSource
```

A Spring-managed object is commonly called a **bean**. Constructor injection makes dependencies explicit and lets tests provide another implementation. It does not mean “make every field static” or “fetch dependencies from a global variable.” We do not need field-level `@Autowired` annotations when there is one constructor.

## Reading the example's annotations

| Annotation | Meaning here |
|---|---|
| `@SpringBootApplication` | Application configuration, auto-configuration, and component discovery. |
| `@RestController` | A component whose handler results are written as HTTP response bodies. |
| `@RequestMapping("/api/persons")` | Common base path for the contact handlers. |
| `@GetMapping`, `@PostMapping`, etc. | Match a method and path to a Java method. |
| `@PathVariable` | Convert a URL segment into a Java method argument. |
| `@RequestBody` | Deserialize the request body into the declared Java input type. |
| `@Table`, `@Column`, `@Id` | Describe the Spring Data JDBC mapping and database identity. |
| `@RestControllerAdvice` | Centralize exception-to-HTTP-response handling. |
| `@ExceptionHandler` | Select the handler for a particular exception type. |

Annotations are metadata interpreted by the framework. Do not memorize them as unexplained punctuation: follow one request from URL to handler, repository, database, and response.

## Controller, input, model, repository

The example deliberately has a small number of responsibilities:

* **`PersonInput`** is a record describing the six editable input fields. It strips outer whitespace and constructs a validated contact value.
* **`Person`** retains the immutable fields and database identity familiar from lab3. It rejects missing/blank or oversized text and missing birthdays.
* **`PersonController`** maps HTTP requests to repository operations and chooses response statuses/headers.
* **`PersonRepository`** extends `CrudRepository`; Spring Data JDBC generates the implementation and SQL from the contract and mapping.
* **`ApiExceptionHandler`** turns selected failures into useful HTTP errors.

We do not add a service layer that merely forwards every call. If business operations later need to combine rules or multiple repository actions, that can motivate a service rather than treating it as compulsory boilerplate.

For creation, `ResponseEntity.created(...)` explicitly sets 201 and a Location header. For deletion, `ResponseEntity.noContent()` returns 204 without pretending there is a JSON body to parse.

## Database connections in a server

Unlike a single-user GUI, a web server may handle multiple requests concurrently. Do not store the current contact, a shared `Connection`, or a `ResultSet` in controller fields. Our controller only retains its repository dependency; each operation owns its local values and JDBC resources.

Spring Boot creates a small **HikariCP connection pool**, exposed through the `DataSource` interface. Spring Data's JDBC infrastructure obtains and releases connections for repository operations. Closing a pooled connection returns it to the pool; it does not necessarily close the underlying physical connection. Spring closes the pool when the application shuts down normally. If we write manual JDBC again, the resource-ownership rules from lab3 still apply.

This pool is supplied infrastructure; configuring a production database workload is not a prerequisite for this lab. The maximum pool size and timeouts are explicit. Ordinary JDBC calls block a web request thread, not a JavaFX event thread, because no GUI runs in this backend. We do not add reactive programming or another executor around these calls.

Spring Data supplies transaction handling for the inherited CRUD operations. This does **not** make an entire controller method or HTTP exchange one transaction. In particular, the controller checks existence before update/delete to provide a clear 404, but another client could change that row between the check and the write. We do not claim that check/write pair is atomic. If a connection is lost near commit, verify the data before retrying a write. The example has no version field or optimistic-locking protocol; more advanced concurrency policies are outside this lab.

## Status codes are part of the contract

| Status | Meaning in this example |
|---|---|
| 200 | Read or update succeeded. |
| 201 | A new contact was created. |
| 204 | Deletion succeeded; the body is empty. |
| 400 | Bad input: invalid ID, missing field, malformed JSON, or invalid date. |
| 404 | The requested contact does not exist. |
| 405 | The path exists but does not support that HTTP method. |
| 415 | The request body uses an unsupported content type. |
| 503 | A database operation failed; inspect server diagnostics and configuration. |

The example maps its database-access failures to 503, including a missing schema. It does not claim all such failures are transient. They must not be mistaken for an empty list or an absent contact. Other unexpected server bugs remain server errors rather than becoming 400 responses.

Error bodies use **Problem Details**, a standard JSON shape carrying fields such as `status`, `title`, and `detail`. SQL diagnostics and stack traces stay in server logs, not the HTTP body. A future JavaFX client can inspect the status and show a useful message instead of parsing every response as a successful contact.

Validation on the server remains necessary even if a future frontend validates a form: another client can call the API directly. In this introductory example, validation uses the existing model rules rather than introducing another annotation-based validation subsystem.

## Configuration and safe local use

`application.properties` maps environment variables to database settings. The same `ADDRESSBOOK_DB_*` names as lab3 let us reuse the course database. The backend and PostgreSQL run as separate processes; closing one does not stop the other.

The server does **not** create `persons` automatically. Run the provided schema script first; seeding is optional. An existing `names` table from lab2 is unrelated. A table with zero rows correctly produces an empty JSON array.

The HTTP listener and published database port are bound locally. This demo has **no authentication or authorization**, and the database user/password are for local coursework. Keeping a password out of the frontend is useful architecture, but does not by itself secure an application. Do not expose this example publicly.

No CORS configuration is needed for curl or a native JavaFX HTTP client. CORS is enforced by browsers for cross-origin requests; it is not an authentication system or a general fix for connection failures.

## Observe the running example

The sections below contain startup commands and ready-to-run curl requests for the completed [Java 25 / Spring Boot example](address-book-backend/), not a tutorial to construct the code. As the instructor demonstrates them, predict:

1. What changes between GET and POST to the same `/api/persons` path?
2. Where does the ID returned by POST come from?
3. Why does PUT's body omit `id`, and why must it contain all six fields?
4. Why is an empty array different from a 503 error?
5. Which machine needs the database credentials after the desktop becomes an HTTP client?
6. Why can repeating POST create a duplicate, while repeating DELETE leaves the same final state?

The next iteration preserves this HTTP contract and changes the desktop application to use it.

## Run the example

Requirements: **JDK 25**, Maven, Docker with Compose V2, and `curl`. No GUI or external Tomcat installation is needed.

From **`code/lab4/`**, start PostgreSQL and ensure the table exists:

```bash
docker compose up -d --wait
docker compose exec -T postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 < sql/01-schema.sql
```

Optionally load sample contacts:

```bash
docker compose exec -T postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 < sql/02-seed.sql
```

**The backend never creates or resets tables automatically.** Lab2's JDBC name-import example creates `names`, not `persons`. An empty `persons` table is valid: GET returns `[]`. The schema script retains existing data; rerunning the optional seed may restore sample contacts you deleted.

For PowerShell, use the container-copy alternative to shell input redirection:

```bash
docker compose cp sql/01-schema.sql postgres:/tmp/01-schema.sql
docker compose exec postgres psql -U addressbook -d addressbook -v ON_ERROR_STOP=1 -f /tmp/01-schema.sql
```

Do the same with `02-seed.sql` only if desired. Labs 2–4 share the same Compose project name and named volume. If you are switching from an earlier lab's Compose copy, first run `docker compose down` there, **without** `--volumes`. Then use this copy; do not run both simultaneously. No existing course data needs to be deleted.

Start the backend in this terminal:

```bash
mvn -f address-book-backend/pom.xml spring-boot:run
```

It listens on **`http://localhost:8080`**, bound to the local loopback interface. A `404` at `/` is normal: this backend provides `/api/persons`, not a home page. Use a second terminal for `curl`.

Alternatively, build and run the executable JAR:

```bash
mvn -f address-book-backend/pom.xml clean package
java -jar address-book-backend/target/lab4-address-book-backend-1.0-SNAPSHOT.jar
```

Choose one startup method, not both at the same time. Ctrl+C stops the backend and closes Spring's connection pool. Stop PostgreSQL separately with `docker compose down`, without deleting volumes.

## Connection settings

The defaults match Compose. Override them in the environment of the backend process if needed:

| Variable | Default |
|---|---|
| `ADDRESSBOOK_DB_URL` | `jdbc:postgresql://localhost:5432/addressbook` |
| `ADDRESSBOOK_DB_USER` | `addressbook` |
| `ADDRESSBOOK_DB_PASSWORD` | `local-course-password` |
| `SERVER_PORT` | `8080` |

If host PostgreSQL port `5432` is occupied, set both `POSTGRES_PORT` for Compose and the JDBC URL for the backend, for example in Linux/macOS/WSL:

```bash
export POSTGRES_PORT=55432
export ADDRESSBOOK_DB_URL=jdbc:postgresql://localhost:55432/addressbook
```

In PowerShell use `$env:POSTGRES_PORT = '55432'` and `$env:ADDRESSBOOK_DB_URL = 'jdbc:postgresql://localhost:55432/addressbook'`. Set `SERVER_PORT` similarly if `8080` is busy, and adjust the curl URLs. These are local exercise credentials, not production settings. Do not publish this unauthenticated demo on a public interface.

## Test the backend with curl

Run the following commands in a **second terminal from `code/lab4/`**. `-i` includes the response status and headers; JSON request bodies come from the included UTF-8 files. These examples need neither Postman nor `jq`. On Windows PowerShell, use **`curl.exe`** in place of `curl` to avoid the legacy alias to `Invoke-WebRequest`.

### List contacts

```bash
curl -i http://localhost:8080/api/persons
```

Expect `200 OK` and a JSON array. A fresh table without seeds returns `[]`; it is not an error.

### Create a contact

```bash
curl -i -X POST http://localhost:8080/api/persons -H "Content-Type: application/json" --data-binary "@requests/new-person.json"
```

Expect **`201 Created`**, a `Location: /api/persons/<id>` header, and a JSON contact containing the generated `id`. The postal code remains the string `"00118"`; the birthday is an ISO date string.

**Write down the returned ID. In every command below, replace `123` with that actual ID. Do not assume it is 1 or 7.** Repeating POST creates another contact: names are not unique in this application's domain.

### Read the created contact

```bash
curl -i http://localhost:8080/api/persons/123
```

Expect `200 OK` and that contact. A browser can perform this GET too, but a browser address bar alone cannot demonstrate the other methods.

### Replace its details

```bash
curl -i -X PUT http://localhost:8080/api/persons/123 -H "Content-Type: application/json" --data-binary "@requests/updated-person.json"
```

Expect `200 OK`, the **same ID**, and city `Modena`. PUT requires all six editable fields; it is not a partial update. The JSON body must not contain `id`: the URL selects the existing contact.

### Delete it and check absence

```bash
curl -i -X DELETE http://localhost:8080/api/persons/123
curl -i http://localhost:8080/api/persons/123
```

Expect **`204 No Content`** with an empty body, followed by **`404 Not Found`**. Deleting the same ID again also returns 404. Repeating the deletion leaves the resource absent, even though the response status differs from the first request.

### Check validation and protocol errors

```bash
curl -i -X POST http://localhost:8080/api/persons -H "Content-Type: application/json" --data-binary "@requests/invalid-person.json"
curl -i http://localhost:8080/api/persons/not-a-number
curl -i http://localhost:8080/api/persons/0
```

All return **`400 Bad Request`**: blank first name, invalid numeric ID, and nonpositive ID respectively. Missing fields, unknown fields, malformed JSON, and an invalid date also return 400. Use `Content-Type: application/json`; unsupported request media types return 415.

Errors use a problem response such as:

```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Contact 123 does not exist",
  "instance": "/api/persons/123"
}
```

The exact `detail` depends on the failure. Normal HTTP failures do not necessarily give curl a nonzero shell exit status: inspect `-i`, or use `--fail-with-body` if your curl version supports it and you want that behavior in scripts.

### Check persistence and database failures

Create another contact, restart only the backend, then GET its ID: it must still exist in PostgreSQL. Delete only that practice contact when finished.

For a local failure demonstration, stop **this course database** with `docker compose stop postgres` and request the list. The request may wait for the configured timeout, then returns **`503 Service Unavailable`**, not a fake empty array. Restart with `docker compose up -d --wait` and retry. Missing `persons` or incorrect database settings also produce 503; check the server log for the underlying cause.

Do not automatically repeat a failed POST: if the connection was lost near commit, the write's outcome may be uncertain. Check the stored data before retrying.

## Automated checks

```bash
mvn -f address-book-backend/pom.xml test
```

Run from `code/lab4/`. Default tests need neither PostgreSQL nor a graphical display. They exercise HTTP routing, JSON, status codes, validation, and error responses with an in-memory repository test double. Spring Data JDBC supplies the running application's repository implementation; the actual generated SQL/PostgreSQL/HTTP flow can be checked using the curl examples above.

## What this example deliberately omits

No JavaFX project, tutorial to construct the application, JPA/ORM, login, authentication, browser frontend, CORS configuration, schema migrations, pagination, or conflict detection for simultaneous edits. Those omissions keep the first encounter with Spring focused on HTTP, dependency injection, and the database boundary. This is a local teaching example, not a production deployment template.

## References

* [Spring Boot 3.5 system requirements](https://docs.spring.io/spring-boot/3.5/system-requirements.html)
* [Spring REST introduction](https://spring.io/guides/gs/rest-service/)
* [Spring dependency injection](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html)
* [Spring Data JDBC repositories](https://docs.spring.io/spring-data/relational/reference/jdbc/repositories.html)
* [HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110)
* [Problem Details for HTTP APIs](https://www.rfc-editor.org/rfc/rfc9457)
* [curl documentation](https://curl.se/docs/)
