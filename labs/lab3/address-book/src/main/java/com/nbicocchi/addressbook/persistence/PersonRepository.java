package com.nbicocchi.addressbook.persistence;

import com.nbicocchi.addressbook.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> findAll();

    Optional<Person> findById(long id);

    Person save(Person person);

    boolean deleteById(long id);
}
