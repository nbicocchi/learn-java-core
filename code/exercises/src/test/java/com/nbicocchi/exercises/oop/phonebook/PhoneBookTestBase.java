package com.nbicocchi.exercises.oop.phonebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class PhoneBookTestBase {
    PhoneBook pb;

    @Test
    void addPerson() {
        assertTrue(pb.addPerson(new Person("Mario", "Rossi", "12345")));
        assertFalse(pb.addPerson(new Person("Mario", "Rossi", "12345")));
        assertArrayEquals(new Person[]{new Person("Mario", "Rossi", "12345")}, pb.searchByLastname("Rossi"));
    }

    @Test
    void removePerson() {
        assertTrue(pb.removePerson(new Person("Marco", "Rizzo", "45243")));
        assertFalse(pb.removePerson(new Person("Marco", "Rizzo", "45243")));
        assertArrayEquals(new Person[]{}, pb.searchByLastname("Rizzo"));
    }

    @Test
    void searchByLastname() {
        assertArrayEquals(new Person[]{new Person("Nicola", "Bicocchi", "34567")}, pb.searchByLastname("Bicocchi"));
        assertArrayEquals(new Person[]{}, pb.searchByLastname("Manzoni"));
    }

    @Test
    void searchByNameAndLastname() {
        assertArrayEquals(new Person[]{new Person("Nicola", "Bicocchi", "34567")},
                pb.searchByNameAndLastname("Nicola", "Bicocchi"));
        pb.addPerson(new Person("Nicola", "Bicocchi", "12346"));
        assertArrayEquals(new Person[]{new Person("Nicola", "Bicocchi", "34567"), new Person("Nicola", "Bicocchi", "12346")},
                pb.searchByNameAndLastname("Nicola", "Bicocchi"));
        assertArrayEquals(new Person[]{}, pb.searchByNameAndLastname("Alessandro", "Manzoni"));
    }

}