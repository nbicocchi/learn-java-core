package com.nbicocchi.addressbook.persistence;

import com.nbicocchi.addressbook.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findAllByOrderByLastNameAscFirstNameAscIdAsc();
}
