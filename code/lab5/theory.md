# Lab 5 — JavaFX as a REST client

## From database access to an HTTP API

This lab adapts the instructor's [Java REST slides](https://github.com/nbicocchi/learn-java-javafx/blob/main/slides/E4.1%20-%20Java%20REST.md) to the address book. Read the [lab4 theory](../lab4/theory.md) for the backend's Spring Data implementation; here we focus on the **client**.

```text
Lab 3:
JavaFX controller → JDBC repository → PostgreSQL

Lab 5:
JavaFX controller → REST client → HTTP/JSON → lab4 backend → Spring Data JDBC → PostgreSQL
```

The same interface still lists, creates, edits, and deletes contacts. The desktop application no longer knows the database URL, user, password, schema, SQL, or PostgreSQL driver. It knows the **API contract** and backend base URL.

Only the frontend is included in this lab. The backend remains in `code/lab4/`; there is no copied server, Docker Compose file, or SQL script in lab5. The [tutorial](tutorial.md) explains how to evolve the lab3 project and run both applications together.

## REST: resources, representations, and operations

**Representational State Transfer (REST)** is an architectural style for distributed applications. Its ideas include identifiable resources, a uniform interface, representations exchanged in messages, stateless requests, cacheability, and layered components. A JSON endpoint is not automatically a complete REST design; our course API is a small REST-style example.

The central concepts are:

| Concept | Address-book example |
|---|---|
| Resource | A particular contact, or the contact collection. |
| Identifier | `/api/persons/42` or `/api/persons`. |
| Representation | JSON describing contact values. |
| Operation | An HTTP method applied to the resource URI. |
| Message | The complete request or response: metadata plus optional body. |

Resources need not correspond one-to-one to a database table. The server could combine data from several tables or services while preserving this API. Neither the table name nor the SQL statement belongs in the client's request.

REST-style APIs can be implemented in different languages; a JavaFX client could use a Python backend if the contract were the same. This separation is the main benefit we demonstrate, not an assertion that every application needs a network boundary.

## Addressing resources

Consider:

```text
http://localhost:8080/api/persons/42
└─ scheme  └─ host:port └─ path
```

`8080` is the backend's HTTP port, not PostgreSQL's `5432`. `localhost` means the computer running the frontend; it would not identify a different computer if the client were moved elsewhere.

Use stable resource identifiers and methods rather than verbs such as `/deletePerson`. The host and scheme are case-insensitive, but **URI paths generally are case-sensitive**: use the API's exact lowercase `/api/persons` path.

Query parameters can express filters or pagination when an API supports them, for example `?city=Roma`. **Lab4 does not implement that filter**, so we do not silently invent it in the client. A request contract must document both supported paths and parameters.

## Requests and responses

An HTTP request includes a method, target, headers, and sometimes a body. The server returns a status, headers, and sometimes a body.

```http
POST /api/persons HTTP/1.1
Host: localhost:8080
Accept: application/json
Content-Type: application/json

{"firstName":"Ada","lastName":"Lovelace","street":"Via REST 1","postalCode":"00118","city":"Roma","birthday":"1995-12-10"}
```

```http
HTTP/1.1 201 Created
Location: /api/persons/42
Content-Type: application/json

{"id":42,"firstName":"Ada","lastName":"Lovelace","street":"Via REST 1","postalCode":"00118","city":"Roma","birthday":"1995-12-10"}
```

These show logical HTTP/1.1 messages; the HTTP client handles wire formatting, connection reuse, and protocol negotiation. `Accept` describes the representation the client wants; `Content-Type` describes the body being sent. A DELETE success in our API is `204 No Content`, **not an empty JSON object**.

## The API contract used by the desktop

| Client operation | HTTP request | Expected success |
|---|---|---|
| List | `GET /api/persons` | 200, JSON array |
| Detail | `GET /api/persons/{id}` | 200, JSON object |
| Create | `POST /api/persons` | 201, object including generated ID |
| Edit | `PUT /api/persons/{id}` | 200, updated object |
| Delete | `DELETE /api/persons/{id}` | 204, no body |

The table already contains full contact values, so selecting a row renders those values locally. The desktop does not need a new HTTP request for each selection. `findById` is implemented and tested as part of the client, but the current master–detail screen does not call it.

PUT replaces all six editable values; it is not a partial update. POST/PUT input bodies **exclude `id`**, because lab4 rejects unknown input fields: creation generates an ID, and modification takes the ID from the path.

### Safe methods and idempotency

GET should not change application data. PUT and DELETE are idempotent in their intended effects. Repeating a DELETE leaves the resource absent, even if the first response was 204 and the second is 404. POST is not idempotent here: repeating creation can produce another contact.

Therefore a timeout does not justify automatically repeating a write. A request may have reached the server and committed even when its response never reached the client. After an uncertain result, reload and inspect the data before deciding what to retry.

## Stateless does not mean “no stored data”

Each request supplies enough information to process it: method, resource identifier, body where needed, and any authentication information if an API requires it. The server should not rely on a hidden “currently selected person” set by a previous request.

The backend can still keep contacts in PostgreSQL. The frontend can still keep table selection and its last loaded list in memory. Statelessness concerns the interaction contract, not the absence of persistent resources or any client-side state.

REST does not forbid backend database transactions. It also does not automatically make several independent HTTP requests one transaction. Our write and the subsequent list refresh are separate operations, just as they were separate repository operations in lab3.

## JSON versus Java objects

The frontend and backend do not share object references, even though both happen to use Java. JSON is a text representation: it has no `LocalDate` type, getters, or constructor behavior.

Our JSON conventions are:

* camelCase property names such as `firstName`;
* numeric `id` only in stored-contact responses;
* a postal code string, preserving `00118`;
* ISO birthday strings such as `2000-02-29`;
* arrays for lists, including `[]` for a successful empty list.

The frontend model remains independent of Spring and JDBC. Two small records inside `RestPersonClient` describe the wire format:

* `PersonInput`: the six request fields, with no ID;
* `PersonResponse`: the seven response fields, including ID, converted to a validated local `Person`.

This avoids accidentally serializing the local object's ID back into an input body. It also makes API assumptions explicit rather than hoping two unrelated Java classes serialize identically.

## Java's HTTP client and Jackson

The JDK supplies `java.net.http.HttpClient`; no external HTTP library is needed. Jackson handles JSON and comes from Maven. The example pins Jackson 2.20.1 as a frontend dependency; its version need not match the backend's internal library version because the boundary is the documented JSON contract.

```java
HttpRequest request = HttpRequest.newBuilder(uri)
        .timeout(Duration.ofSeconds(10))
        .header("Accept", "application/json")
        .GET()
        .build();

HttpResponse<String> response = http.send(request,
        HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
```

`send` blocks until completion or failure. That is acceptable on our dedicated worker, **not on the JavaFX Application Thread**. One reused `HttpClient` handles connections; one configured `ObjectMapper` handles serialization.

```java
ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
```

The Java-time module understands `LocalDate`; disabling timestamp-style output keeps dates as ISO strings instead of numeric arrays. A `TypeReference<List<PersonResponse>>` retains enough generic type information to deserialize each list item as the correct record rather than a generic map.

With modules, the project requires `java.net.http` and the Jackson modules. Only the client package is opened to Jackson's reflective record conversion; only the controller package is opened to FXML. There is no `java.sql` or PostgreSQL module requirement.

## Three kinds of failure

**1. No usable HTTP response:** refused connection, timeout, or interruption. The backend may be stopped or unreachable. There is no HTTP status code; our exception uses `0` to represent that absence. Interrupted operations restore the thread's interrupted flag.

**2. An HTTP failure response:** 400 for invalid input, 404 for a missing contact, or 503 when lab4 cannot access its database. A 503 proves the HTTP server answered; it is not the same as connection refused.

**3. An unexpected successful response:** wrong JSON shape, missing IDs, malformed JSON, wrong content type, or a status different from the documented success. We report a protocol/representation problem instead of treating it as a successful empty list.

The client checks status **before** decoding a contact. Error responses can contain Problem Details JSON, whose `detail` is useful for the user. They can also be empty or contain HTML from an intermediary; the client retains a safe fallback instead of attempting to display raw HTML as a contact. Displayed server details are length-limited.

## Preserving the responsive UI

The task helper from lab3 is retained, renamed to `runRemoteAction`:

1. Capture the confirmed contact value or ID on the JavaFX thread.
2. Disable content controls and show the operation in the status bar.
3. On the worker, invoke the HTTP client, then refresh the collection.
4. On JavaFX success, replace list contents and clear stale selection.
5. On failure, keep the displayed data, show an error, and allow Reload/retry.

The list is a snapshot, not an automatically synchronized database view. Another client may change a contact after it was loaded. There is no polling or push subscription in this lab; explicit Reload retrieves current values.

The connect timeout is three seconds and each HTTP request timeout is ten seconds. A write followed by a refresh comprises two requests, not one ten-second budget. The application does not implement automatic write retries or redirect following. On shutdown, it requests worker interruption and shuts down the HTTP client without waiting on the JavaFX thread for a graceful drain.

Form validation still improves local usability, but the backend must validate independently. The frontend is not a trusted enforcement boundary.

## Benefits, costs, and boundaries

The API hides database details and supports different clients. HTTP also adds serialization, network latency, failure cases, and another process to operate. Avoid claiming it is always faster or simpler than direct JDBC.

This client uses HTTP request/response. Polling, server-sent events, WebSockets, and publish/subscribe are possible adjacent designs, but are not provided automatically by declaring an API RESTful. Caching can reduce requests when a server defines appropriate cache semantics; lab4 has not implemented cache validation.

A native JavaFX client is not subject to browser CORS enforcement. No CORS configuration needs to be added to lab4 for this lab. Authentication and TLS deployment remain outside the local demonstration; do not expose the unauthenticated course backend publicly.

## Check your understanding

1. Why does the desktop no longer need PostgreSQL credentials?
2. Why must an edited contact's ID be in the URL, but not its JSON body?
3. How do a successful empty array, an HTTP 503, and a connection failure differ?
4. Why is retrying a timed-out POST potentially unsafe?
5. Which lab3 files can remain unchanged when the transport changes?
6. Why does JSON interoperability not require identical Java classes or Jackson versions on both sides?

Continue with the [tutorial](tutorial.md).

## References

* [Instructor's Java REST slides](https://github.com/nbicocchi/learn-java-javafx/blob/main/slides/E4.1%20-%20Java%20REST.md), adapted to the course API, Java 25, and JDK HTTP client.
* [Lab4 backend theory and curl examples](../lab4/theory.md)
* [Java 25 HttpClient API](https://docs.oracle.com/en/java/javase/25/docs/api/java.net.http/java/net/http/HttpClient.html)
* [HTTP semantics](https://www.rfc-editor.org/rfc/rfc9110)
* [Problem Details](https://www.rfc-editor.org/rfc/rfc9457)
