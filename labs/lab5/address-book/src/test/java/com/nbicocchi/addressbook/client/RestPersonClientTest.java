package com.nbicocchi.addressbook.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbicocchi.addressbook.model.Person;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class RestPersonClientTest {
    private static final String PERSON = """
            {"id":7,"firstName":"Zoë","lastName":"D'Angelo","street":"Via HTTP 1",
             "postalCode":"00118","city":"Roma","birthday":"2000-02-29"}
            """;
    private final ObjectMapper mapper = new ObjectMapper();
    private final LinkedBlockingQueue<Reply> replies = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<RecordedRequest> requests = new LinkedBlockingQueue<>();
    private HttpServer server;
    private RestPersonClient client;

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/api/persons", this::handle);
        server.start();
        client = new RestPersonClient(baseUrl());
    }

    @AfterEach
    void stopServer() {
        client.close();
        server.stop(0);
    }

    @Test
    void listAndDetailUseGetAndMapUtf8DatesAndPostalCodes() throws Exception {
        replies.add(new Reply(200, "[" + PERSON + "]"));
        List<Person> people = client.findAll();
        assertEquals(1, people.size());
        assertEquals("Zoë", people.getFirst().getFirstName());
        assertEquals("D'Angelo", people.getFirst().getLastName());
        assertEquals("00118", people.getFirst().getPostalCode());
        assertEquals(LocalDate.of(2000, 2, 29), people.getFirst().getBirthday());
        RecordedRequest list = takeRequest();
        assertEquals("GET", list.method());
        assertEquals("/api/persons", list.path());
        assertEquals("application/json", list.accept());

        replies.add(new Reply(200, PERSON));
        assertEquals(7L, client.findById(7).getId());
        assertEquals("/api/persons/7", takeRequest().path());
    }

    @Test
    void createAndUpdateSendSixFieldsWithoutIdAndCheckTheirStatuses() throws Exception {
        Person draft = new Person("Zoë", "D'Angelo", "Via HTTP 1", "00118", "Roma", LocalDate.of(2000, 2, 29));
        replies.add(new Reply(201, PERSON));
        Person saved = client.create(draft);
        assertNull(draft.getId());
        assertEquals(7L, saved.getId());
        RecordedRequest post = takeRequest();
        assertEquals("POST", post.method());
        assertEquals("application/json", post.contentType());
        JsonNode input = mapper.readTree(post.body());
        assertEquals(6, input.size());
        assertFalse(input.has("id"));
        assertEquals("2000-02-29", input.get("birthday").asText());
        assertEquals("00118", input.get("postalCode").asText());

        replies.add(new Reply(200, PERSON));
        client.update(saved);
        RecordedRequest put = takeRequest();
        assertEquals("PUT", put.method());
        assertEquals("/api/persons/7", put.path());
        assertFalse(mapper.readTree(put.body()).has("id"));
    }

    @Test
    void deleteAcceptsAnEmpty204WithoutParsingJson() throws Exception {
        replies.add(new Reply(204, ""));
        assertDoesNotThrow(() -> client.deleteById(7));
        assertEquals("DELETE", takeRequest().method());
    }

    @Test
    void emptyArrayIsValidButInvalidSuccessRepresentationsAreErrors() {
        replies.add(new Reply(200, "[]"));
        assertTrue(client.findAll().isEmpty());
        for (String body : List.of("null", "{}", "[null]", "[{}]", "not-json")) {
            replies.add(new Reply(200, body));
            assertThrows(RestClientException.class, client::findAll);
        }
        replies.add(new Reply(200, "<html>not the API</html>", "text/html", 0));
        assertThrows(RestClientException.class, client::findAll);
    }

    @Test
    void httpErrorsKeepStatusAndProblemDetailAndNeverBecomeEmptyLists() throws Exception {
        for (int status : List.of(400, 404, 503)) {
            replies.add(new Reply(status, "{\"detail\":\"A useful server message\"}", "application/problem+json", 0));
            RestClientException error = assertThrows(RestClientException.class, client::findAll);
            assertEquals(status, error.getStatusCode());
            assertTrue(error.getMessage().contains("A useful server message"));
            takeRequest();
        }
        replies.add(new Reply(502, "<html>Proxy failed</html>", "text/html", 0));
        RestClientException error = assertThrows(RestClientException.class, client::findAll);
        assertEquals(502, error.getStatusCode());
        assertFalse(error.getMessage().contains("<html>"));
    }

    @Test
    void unexpectedStatusesAreNotSilentlyAcceptedOrRetried() throws Exception {
        replies.add(new Reply(200, PERSON)); // POST requires 201, not an arbitrary 2xx.
        assertThrows(RestClientException.class, () -> client.create(
                new Person("Zoë", "D'Angelo", "Street", "00118", "Roma", LocalDate.of(2000, 2, 29))));
        assertEquals("POST", takeRequest().method());
        assertNull(requests.poll(100, TimeUnit.MILLISECONDS));
    }

    @Test
    void refusedConnectionsAndTimeoutsHaveNoHttpStatus() throws Exception {
        int unusedPort;
        try (ServerSocket socket = new ServerSocket(0)) {
            unusedPort = socket.getLocalPort();
        }
        try (RestPersonClient unavailable = new RestPersonClient("http://127.0.0.1:" + unusedPort)) {
            assertEquals(0, assertThrows(RestClientException.class, unavailable::findAll).getStatusCode());
        }
        replies.add(new Reply(200, "[]", "application/json", 400));
        try (RestPersonClient impatient = new RestPersonClient(baseUrl(), Duration.ofSeconds(1), Duration.ofMillis(100))) {
            assertEquals(0, assertThrows(RestClientException.class, impatient::findAll).getStatusCode());
        }
    }

    @Test
    void interruptionIsPreserved() throws Exception {
        replies.add(new Reply(200, "[]", "application/json", 400));
        AtomicReference<Throwable> result = new AtomicReference<>();
        Thread worker = new Thread(() -> {
            try {
                client.findAll();
                result.set(new AssertionError("Expected interruption"));
            } catch (RestClientException error) {
                if (error.getStatusCode() != 0 || !Thread.currentThread().isInterrupted()) {
                    result.set(new AssertionError("Interruption was not preserved", error));
                }
            }
        });
        worker.start();
        takeRequest();
        worker.interrupt();
        worker.join(3000);
        assertFalse(worker.isAlive());
        assertNull(result.get());
    }

    private String baseUrl() {
        return "http://127.0.0.1:" + server.getAddress().getPort();
    }

    private RecordedRequest takeRequest() throws InterruptedException {
        RecordedRequest request = requests.poll(3, TimeUnit.SECONDS);
        assertNotNull(request, "Expected an HTTP request");
        return request;
    }

    private void handle(HttpExchange exchange) throws IOException {
        try (exchange) {
            requests.add(new RecordedRequest(exchange.getRequestMethod(), exchange.getRequestURI().getPath(),
                    exchange.getRequestHeaders().getFirst("Content-Type"), exchange.getRequestHeaders().getFirst("Accept"),
                    new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8)));
            Reply reply = replies.poll();
            if (reply == null) {
                exchange.sendResponseHeaders(500, -1);
                return;
            }
            if (reply.delayMillis() > 0) {
                try {
                    Thread.sleep(reply.delayMillis());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            byte[] body = reply.body().getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", reply.contentType());
            exchange.sendResponseHeaders(reply.status(), reply.status() == 204 ? -1 : body.length);
            if (reply.status() != 204) {
                exchange.getResponseBody().write(body);
            }
        }
    }

    private record Reply(int status, String body, String contentType, int delayMillis) {
        Reply(int status, String body) { this(status, body, "application/json", 0); }
    }

    private record RecordedRequest(String method, String path, String contentType, String accept, String body) { }
}
