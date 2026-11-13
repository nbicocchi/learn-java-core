package com.nbicocchi.addressbook.persistence;

import com.nbicocchi.addressbook.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class JdbcPersonRepository implements PersonRepository {
    private static final String COLUMNS = "id, first_name, last_name, street, postal_code, city, birthday";
    private final DatabaseConfig database;

    public JdbcPersonRepository(DatabaseConfig database) {
        this.database = database;
    }

    @Override
    public List<Person> findAll() {
        String sql = "SELECT " + COLUMNS + " FROM persons ORDER BY last_name, first_name, id";
        try (Connection connection = database.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            List<Person> people = new ArrayList<>();
            while (rows.next()) {
                people.add(map(rows));
            }
            return people;
        } catch (SQLException e) {
            throw new DataAccessException("Could not load contacts", e);
        }
    }

    @Override
    public Optional<Person> findById(long id) {
        String sql = "SELECT " + COLUMNS + " FROM persons WHERE id = ?";
        try (Connection connection = database.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rows = statement.executeQuery()) {
                return rows.next() ? Optional.of(map(rows)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Could not find contact", e);
        }
    }

    @Override
    public Person save(Person person) {
        boolean insert = person.getId() == null;
        String sql = insert
                ? "INSERT INTO persons (first_name, last_name, street, postal_code, city, birthday) "
                    + "VALUES (?, ?, ?, ?, ?, ?) RETURNING " + COLUMNS
                : "UPDATE persons SET first_name=?, last_name=?, street=?, postal_code=?, city=?, birthday=? "
                    + "WHERE id=? RETURNING " + COLUMNS;
        try (Connection connection = database.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setString(3, person.getStreet());
            statement.setString(4, person.getPostalCode());
            statement.setString(5, person.getCity());
            statement.setObject(6, person.getBirthday());
            if (!insert) {
                statement.setLong(7, person.getId());
            }
            try (ResultSet rows = statement.executeQuery()) {
                if (!rows.next()) {
                    throw new DataAccessException("The contact no longer exists; reload the list", null);
                }
                return map(rows);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Could not save contact", e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        try (Connection connection = database.openConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM persons WHERE id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataAccessException("Could not delete contact", e);
        }
    }

    private static Person map(ResultSet row) throws SQLException {
        return new Person(row.getLong("id"), row.getString("first_name"), row.getString("last_name"),
                row.getString("street"), row.getString("postal_code"), row.getString("city"),
                row.getObject("birthday", LocalDate.class));
    }
}
