package com.nbicocchi.addressbook.model;

import java.time.LocalDate;
import java.util.Objects;

/** An immutable contact; a null ID represents a contact not yet stored. */
public final class Person {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String street;
    private final String postalCode;
    private final String city;
    private final LocalDate birthday;

    public Person(String firstName, String lastName, String street,
                  String postalCode, String city, LocalDate birthday) {
        this(null, firstName, lastName, street, postalCode, city, birthday);
    }

    public Person(Long id, String firstName, String lastName, String street,
                  String postalCode, String city, LocalDate birthday) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
        this.firstName = required(firstName, "First name", 80);
        this.lastName = required(lastName, "Last name", 80);
        this.street = required(street, "Street", 160);
        this.postalCode = required(postalCode, "Postal code", 20);
        this.city = required(city, "City", 100);
        this.birthday = Objects.requireNonNull(birthday);
    }

    private static String required(String value, String field, int maxLength) {
        if (value == null || value.isBlank() || value.codePointCount(0, value.length()) > maxLength) {
            throw new IllegalArgumentException(field + " is required (maximum " + maxLength + " characters)");
        }
        return value;
    }

    public Long getId() {
        return id;
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
