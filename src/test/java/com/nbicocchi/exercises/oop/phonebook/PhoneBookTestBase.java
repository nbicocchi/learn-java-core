package com.nbicocchi.exercises.oop.phonebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

abstract class PhoneBookTestBase {
    PhoneBook pb;

    @Test
    void addPerson() {
        pb.addPerson(new Person("Mario", "Rossi", "12345"));
        assertEquals(pb.searchByName("Mario"), new Person("Mario", "Rossi", "12345"));
    }

    @Test
    void deleteByNumber() {
        pb.removePerson(new Person("Marco", "Rizzo", "45243"));
        assertNull(pb.searchByLastname("Rizzo"));
    }

    @Test
    void searchByName() {
        assertEquals(new Person("Nicola", "Bicocchi", "34567"), pb.searchByName("Nicola"));
    }

    @Test
    void searchByLastname() {
        assertEquals(new Person("Nicola", "Bicocchi", "34567"), pb.searchByLastname("Bicocchi"));
    }

    @Test
    void searchByNumber() {
        assertEquals(new Person("Nicola", "Bicocchi", "34567"), pb.searchByNumber("34567"));
    }
}