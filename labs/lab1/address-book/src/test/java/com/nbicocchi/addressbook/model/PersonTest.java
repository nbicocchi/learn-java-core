package com.nbicocchi.addressbook.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void constructorPreservesContactDetailsAndLeadingZeroInPostalCode() {
        Person person = new Person("Luca", "Bianchi", "Via Roma 8", "00118",
                "Roma", LocalDate.of(1995, 7, 22));

        assertEquals("Luca", person.getFirstName());
        assertEquals("Bianchi", person.getLastName());
        assertEquals("Via Roma 8", person.getStreet());
        assertEquals("00118", person.getPostalCode());
        assertEquals("Roma", person.getCity());
        assertEquals(LocalDate.of(1995, 7, 22), person.getBirthday());
    }

    @Test
    void sampleDataContainsSixContacts() {
        assertEquals(6, SampleContacts.create().size());
        assertEquals("Anna", SampleContacts.create().getFirst().getFirstName());
    }

    @Test
    void sampleListsAreIndependent() {
        var first = SampleContacts.create();
        var second = SampleContacts.create();

        first.clear();

        assertEquals(6, second.size());
        assertEquals(6, SampleContacts.create().size());
    }
}
