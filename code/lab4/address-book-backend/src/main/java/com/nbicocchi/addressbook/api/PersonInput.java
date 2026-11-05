package com.nbicocchi.addressbook.api;

import com.nbicocchi.addressbook.model.Person;

import java.time.LocalDate;

/** Input has no ID: POST gets a generated ID, PUT takes the ID from the URL. */
public record PersonInput(String firstName, String lastName, String street,
                          String postalCode, String city, LocalDate birthday) {
    public Person toPerson(Long id) {
        return new Person(id, trimmed(firstName), trimmed(lastName), trimmed(street),
                trimmed(postalCode), trimmed(city), birthday);
    }

    private static String trimmed(String value) {
        return value == null ? null : value.strip();
    }
}
