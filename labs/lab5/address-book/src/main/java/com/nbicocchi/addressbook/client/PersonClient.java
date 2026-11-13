package com.nbicocchi.addressbook.client;

import com.nbicocchi.addressbook.model.Person;

import java.util.List;

/** Application-facing HTTP operations; no JDBC types or database configuration. */
public interface PersonClient {
    List<Person> findAll();
    Person findById(long id);
    Person create(Person draft);
    Person update(Person person);
    void deleteById(long id);
}
