package com.nbicocchi.addressbook.persistence;

import com.nbicocchi.addressbook.model.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/** Opt-in: creates and removes only its own randomly named test schema. */
@EnabledIfSystemProperty(named = "jdbc.integration", matches = "true")
class JdbcPersonRepositoryTest {
    private static final String SCHEMA = "lab3_test_" + UUID.randomUUID().toString().replace("-", "");
    private static JdbcPersonRepository repository;

    @BeforeAll
    static void prepareSchema() throws Exception {
        DatabaseConfig base = DatabaseConfig.fromEnvironment();
        try (Connection connection = base.openConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE SCHEMA " + SCHEMA);
            statement.executeUpdate("CREATE TABLE " + SCHEMA + ".persons ("
                    + "id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                    + "first_name VARCHAR(80) NOT NULL, last_name VARCHAR(80) NOT NULL, "
                    + "street VARCHAR(160) NOT NULL, postal_code VARCHAR(20) NOT NULL, "
                    + "city VARCHAR(100) NOT NULL, birthday DATE NOT NULL)");
        }
        String url = System.getenv().getOrDefault("ADDRESSBOOK_DB_URL", "jdbc:postgresql://localhost:5432/addressbook");
        url += (url.contains("?") ? "&" : "?") + "currentSchema=" + SCHEMA;
        DatabaseConfig isolated = new DatabaseConfig(url,
                System.getenv().getOrDefault("ADDRESSBOOK_DB_USER", "addressbook"),
                System.getenv().getOrDefault("ADDRESSBOOK_DB_PASSWORD", "local-course-password"));
        repository = new JdbcPersonRepository(isolated);
    }

    @AfterAll
    static void removeOnlyTestSchema() throws Exception {
        try (Connection connection = DatabaseConfig.fromEnvironment().openConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP SCHEMA IF EXISTS " + SCHEMA + " CASCADE");
        }
    }

    @Test
    void insertReadUpdateDeleteAndMissingId() {
        Person draft = new Person("D'Angelo", "O'Neil", "Via SQL 1", "00118", "Roma", LocalDate.of(2000, 2, 29));
        Person saved = repository.save(draft);
        assertNull(draft.getId());
        assertNotNull(saved.getId());
        Person loaded = repository.findById(saved.getId()).orElseThrow();
        assertEquals("D'Angelo", loaded.getFirstName());
        assertEquals("00118", loaded.getPostalCode());
        assertEquals(LocalDate.of(2000, 2, 29), loaded.getBirthday());
        assertEquals(1, repository.findAll().size());

        Person changed = new Person(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getStreet(),
                saved.getPostalCode(), "Modena", saved.getBirthday());
        assertEquals(saved.getId(), repository.save(changed).getId());
        assertEquals("Modena", repository.findById(saved.getId()).orElseThrow().getCity());
        assertTrue(repository.deleteById(saved.getId()));
        assertFalse(repository.deleteById(saved.getId()));
        assertTrue(repository.findById(saved.getId()).isEmpty());
        assertThrows(DataAccessException.class, () -> repository.save(changed));
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void connectionFailurePreservesSqlCause() {
        JdbcPersonRepository unavailable = new JdbcPersonRepository(
                new DatabaseConfig("jdbc:postgresql://127.0.0.1:1/unavailable", "none", "none"));
        DataAccessException error = assertThrows(DataAccessException.class, unavailable::findAll);
        assertInstanceOf(java.sql.SQLException.class, error.getCause());
    }
}
