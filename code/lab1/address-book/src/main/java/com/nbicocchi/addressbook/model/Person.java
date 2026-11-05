package com.nbicocchi.addressbook.model;

import java.time.LocalDate;
import java.util.Objects;

/** A read-only contact for the first, in-memory checkpoint. */
public final class Person {
    private final String firstName;
    private final String lastName;
    private final String street;
    private final String postalCode;
    private final String city;
    private final LocalDate birthday;

    public Person(String firstName, String lastName, String street,
                  String postalCode, String city, LocalDate birthday) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.street = Objects.requireNonNull(street);
        this.postalCode = Objects.requireNonNull(postalCode);
        this.city = Objects.requireNonNull(city);
        this.birthday = Objects.requireNonNull(birthday);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
