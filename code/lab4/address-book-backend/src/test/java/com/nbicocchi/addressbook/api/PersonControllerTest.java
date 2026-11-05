package com.nbicocchi.addressbook.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nbicocchi.addressbook.model.Person;
import com.nbicocchi.addressbook.model.PersonNotFoundException;
import org.springframework.dao.DataAccessResourceFailureException;
import com.nbicocchi.addressbook.persistence.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Comparator;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PersonControllerTest {
    private static final String JSON = """
            {"firstName":"D'Angelo","lastName":"Rossi","street":"Via Test 1",
             "postalCode":"00118","city":"Roma","birthday":"2000-02-29"}
            """;
    private MockMvc mvc;
    private FakeRepository repository;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        repository = new FakeRepository();
        mapper = Jackson2ObjectMapperBuilder.json()
                .featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).build();
        mvc = MockMvcBuilders.standaloneSetup(new PersonController(repository))
                .setControllerAdvice(new ApiExceptionHandler())
                .setMessageConverters(new MappingJackson2HttpMessageConverter(mapper))
                .build();
    }

    @Test
    void emptyCollectionIsAnArrayNotAnError() throws Exception {
        mvc.perform(get("/api/persons"))
                .andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void createReadUpdateDeleteContract() throws Exception {
        String body = mvc.perform(post("/api/persons").contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/persons/1"))
                .andExpect(jsonPath("$.postalCode").value("00118"))
                .andExpect(jsonPath("$.birthday").value("2000-02-29"))
                .andReturn().getResponse().getContentAsString();
        long id = mapper.readTree(body).get("id").asLong();
        mvc.perform(get("/api/persons/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("D'Angelo"));
        mvc.perform(put("/api/persons/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.replace("Roma", "Modena")))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.city").value("Modena"));
        mvc.perform(delete("/api/persons/{id}", id))
                .andExpect(status().isNoContent()).andExpect(content().string(""));
        mvc.perform(get("/api/persons/{id}", id)).andExpect(status().isNotFound());
        mvc.perform(delete("/api/persons/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    void absentIdsAre404IncludingPutWithoutUpsert() throws Exception {
        mvc.perform(get("/api/persons/99")).andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(404));
        mvc.perform(put("/api/persons/99").contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isNotFound());
        mvc.perform(delete("/api/persons/99")).andExpect(status().isNotFound());
        mvc.perform(get("/api/persons")).andExpect(content().json("[]"));
    }

    @Test
    void missingBlankOversizedAndUnknownFieldsAre400() throws Exception {
        for (String input : List.of("{}", JSON.replace("D'Angelo", " "),
                JSON.replace("D'Angelo", "A".repeat(81)),
                JSON.replace("\"2000-02-29\"", "null"),
                JSON.replace("{", "{\"id\":7,"))) {
            mvc.perform(post("/api/persons").contentType(MediaType.APPLICATION_JSON).content(input))
                    .andExpect(status().isBadRequest());
        }
    }

    @Test
    void malformedJsonAndDatesAre400() throws Exception {
        for (String input : List.of("{", JSON.replace("2000-02-29", "not-a-date"), "null")) {
            mvc.perform(post("/api/persons").contentType(MediaType.APPLICATION_JSON).content(input))
                    .andExpect(status().isBadRequest());
        }
    }

    @Test
    void nonNumericAndNonpositiveIdsAre400() throws Exception {
        for (String id : List.of("not-a-number", "0", "-1", "999999999999999999999")) {
            mvc.perform(get("/api/persons/" + id)).andExpect(status().isBadRequest());
        }
    }

    @Test
    void databaseFailuresAre503WithoutLeakingSqlDiagnostics() throws Exception {
        repository.unavailable = true;
        mvc.perform(get("/api/persons")).andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.status").value(503))
                .andExpect(content().string(not(containsString("private database diagnostic"))));
    }

    @Test
    void unsupportedMethodsAndContentTypesAreNotSuccesses() throws Exception {
        mvc.perform(patch("/api/persons/1").contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isMethodNotAllowed());
        mvc.perform(post("/api/persons").contentType(MediaType.TEXT_PLAIN).content(JSON))
                .andExpect(status().isUnsupportedMediaType());
    }

    private static class FakeRepository implements PersonRepository {
        private final Map<Long, Person> people = new LinkedHashMap<>();
        private long nextId = 1;
        private boolean unavailable;

        @Override
        public List<Person> findAllByOrderByLastNameAscFirstNameAscIdAsc() {
            if (unavailable) {
                throw new DataAccessResourceFailureException("Could not load contacts", new SQLException("private database diagnostic"));
            }
            return people.values().stream().sorted(Comparator.comparing(Person::getLastName)
                    .thenComparing(Person::getFirstName).thenComparing(Person::getId)).toList();
        }

        @Override
        public Optional<Person> findById(Long id) {
            return Optional.ofNullable(people.get(id));
        }

        @Override
        @SuppressWarnings("unchecked")
        public <S extends Person> S save(S person) {
            Long id = person.getId();
            if (id == null) {
                id = nextId++;
            } else if (!people.containsKey(id)) {
                throw new PersonNotFoundException(id);
            }
            Person saved = new Person(id, person.getFirstName(), person.getLastName(), person.getStreet(),
                    person.getPostalCode(), person.getCity(), person.getBirthday());
            people.put(id, saved);
            return (S) saved;
        }

        @Override
        public void deleteById(Long id) {
            people.remove(id);
        }

        @Override
        public boolean existsById(Long id) { return people.containsKey(id); }

        @Override
        public Iterable<Person> findAll() { return findAllByOrderByLastNameAscFirstNameAscIdAsc(); }

        @Override
        public long count() { return people.size(); }

        @Override
        public <S extends Person> Iterable<S> saveAll(Iterable<S> entities) {
            List<S> saved = new ArrayList<>();
            entities.forEach(entity -> saved.add(save(entity)));
            return saved;
        }

        @Override
        public Iterable<Person> findAllById(Iterable<Long> ids) {
            List<Person> found = new ArrayList<>();
            ids.forEach(id -> findById(id).ifPresent(found::add));
            return found;
        }

        @Override
        public void delete(Person person) { deleteById(person.getId()); }

        @Override
        public void deleteAllById(Iterable<? extends Long> ids) { ids.forEach(this::deleteById); }

        @Override
        public void deleteAll(Iterable<? extends Person> entities) { entities.forEach(this::delete); }

        @Override
        public void deleteAll() { people.clear(); }
    }
}
