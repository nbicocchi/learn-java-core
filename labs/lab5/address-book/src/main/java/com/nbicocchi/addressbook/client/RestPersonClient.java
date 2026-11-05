package com.nbicocchi.addressbook.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nbicocchi.addressbook.model.Person;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class RestPersonClient implements PersonClient, AutoCloseable {
    private final URI collection;
    private final Duration requestTimeout;
    private final HttpClient http;
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public RestPersonClient(String baseUrl) {
        this(baseUrl, Duration.ofSeconds(3), Duration.ofSeconds(10));
    }

    public RestPersonClient(String baseUrl, Duration connectTimeout, Duration requestTimeout) {
        URI base = URI.create(baseUrl.strip());
        if (!("http".equalsIgnoreCase(base.getScheme()) || "https".equalsIgnoreCase(base.getScheme()))
                || base.getHost() == null || base.getUserInfo() != null
                || base.getQuery() != null || base.getFragment() != null) {
            throw new IllegalArgumentException("ADDRESSBOOK_API_URL must be an HTTP(S) base URL without credentials, query, or fragment");
        }
        collection = URI.create(base.toString().replaceAll("/+$", "") + "/api/persons");
        this.requestTimeout = Objects.requireNonNull(requestTimeout);
        http = HttpClient.newBuilder().connectTimeout(connectTimeout)
                .followRedirects(HttpClient.Redirect.NEVER).build();
    }

    @Override
    public List<Person> findAll() {
        HttpResponse<String> response = send(request(collection).GET().build(), 200);
        requireJson(response);
        try {
            List<PersonResponse> values = mapper.readValue(response.body(), new TypeReference<>() { });
            return Objects.requireNonNull(values).stream().map(PersonResponse::toPerson).toList();
        } catch (JsonProcessingException | IllegalArgumentException | NullPointerException e) {
            throw invalidResponse(response, e);
        }
    }

    @Override
    public Person findById(long id) {
        return readPerson(send(request(item(id)).GET().build(), 200));
    }

    @Override
    public Person create(Person draft) {
        if (draft.getId() != null) {
            throw new IllegalArgumentException("A new contact must not have an ID");
        }
        return readPerson(send(request(collection).header("Content-Type", "application/json")
                .POST(jsonBody(draft)).build(), 201));
    }

    @Override
    public Person update(Person person) {
        if (person.getId() == null) {
            throw new IllegalArgumentException("An updated contact must have an ID");
        }
        return readPerson(send(request(item(person.getId())).header("Content-Type", "application/json")
                .PUT(jsonBody(person)).build(), 200));
    }

    @Override
    public void deleteById(long id) {
        // A 204 has no JSON representation to deserialize.
        send(request(item(id)).DELETE().build(), 204);
    }

    private URI item(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        return URI.create(collection + "/" + id);
    }

    private HttpRequest.Builder request(URI uri) {
        return HttpRequest.newBuilder(uri).timeout(requestTimeout).header("Accept", "application/json");
    }

    private HttpRequest.BodyPublisher jsonBody(Person person) {
        // Do not serialize Person directly: lab4 rejects an id in POST/PUT bodies.
        PersonInput input = new PersonInput(person.getFirstName(), person.getLastName(), person.getStreet(),
                person.getPostalCode(), person.getCity(), person.getBirthday());
        try {
            return HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(input), StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RestClientException("Could not encode the contact as JSON.", 0, e);
        }
    }

    private HttpResponse<String> send(HttpRequest request, int expectedStatus) {
        HttpResponse<String> response;
        try {
            response = http.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RestClientException("The HTTP operation was interrupted.", 0, e);
        } catch (IOException e) {
            throw new RestClientException("Cannot reach the backend or the request timed out. Check lab4 and ADDRESSBOOK_API_URL.", 0, e);
        }
        if (response.statusCode() != expectedStatus) {
            throw httpError(response);
        }
        return response;
    }

    private Person readPerson(HttpResponse<String> response) {
        requireJson(response);
        try {
            return Objects.requireNonNull(mapper.readValue(response.body(), PersonResponse.class)).toPerson();
        } catch (JsonProcessingException | IllegalArgumentException | NullPointerException e) {
            throw invalidResponse(response, e);
        }
    }

    private void requireJson(HttpResponse<String> response) {
        String type = response.headers().firstValue("Content-Type").orElse("")
                .split(";", 2)[0].strip().toLowerCase(Locale.ROOT);
        if (!type.equals("application/json") && !(type.startsWith("application/") && type.endsWith("+json"))) {
            throw invalidResponse(response, null);
        }
    }

    private RestClientException invalidResponse(HttpResponse<String> response, Throwable cause) {
        return new RestClientException("The backend returned an unexpected contact representation. Check the API URL and version.",
                response.statusCode(), cause);
    }

    private RestClientException httpError(HttpResponse<String> response) {
        String detail = switch (response.statusCode()) {
            case 400 -> "The backend rejected the input.";
            case 404 -> "The contact no longer exists. Reload the list.";
            case 503 -> "The backend cannot access its database.";
            default -> "Unexpected response from the backend.";
        };
        try {
            JsonNode problem = mapper.readTree(response.body());
            if (problem != null && problem.path("detail").isTextual()) {
                String supplied = problem.path("detail").asText();
                detail = supplied.substring(0, Math.min(supplied.length(), 600));
            }
        } catch (JsonProcessingException ignored) {
            // A proxy may return HTML or an empty body; retain a useful fallback.
        }
        return new RestClientException("HTTP " + response.statusCode() + ": " + detail, response.statusCode(), null);
    }

    @Override
    public void close() {
        http.shutdownNow();
    }

    private record PersonInput(String firstName, String lastName, String street,
                               String postalCode, String city, LocalDate birthday) { }

    private record PersonResponse(Long id, String firstName, String lastName, String street,
                                  String postalCode, String city, LocalDate birthday) {
        Person toPerson() {
            if (id == null) {
                throw new IllegalArgumentException("A stored contact must have an ID");
            }
            return new Person(id, firstName, lastName, street, postalCode, city, birthday);
        }
    }
}
