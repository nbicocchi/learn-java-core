package com.nbicocchi.addressbook.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

/** Spring Data JDBC maps this aggregate to the existing lab3 table. */
@Table("persons")
public final class Person {
    @Id
    private final Long id;
    @Column("first_name")
    private final String firstName;
    @Column("last_name")
    private final String lastName;
    private final String street;
    @Column("postal_code")
    private final String postalCode;
    private final String city;
    private final LocalDate birthday;

    public Person(Long id, String firstName, String lastName, String street,
                  String postalCode, String city, LocalDate birthday) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (birthday == null) {
            throw new IllegalArgumentException("Birthday is required");
        }
        this.id = id;
        this.firstName = required(firstName, "First name", 80);
        this.lastName = required(lastName, "Last name", 80);
        this.street = required(street, "Street", 160);
        this.postalCode = required(postalCode, "Postal code", 20);
        this.city = required(city, "City", 100);
        this.birthday = birthday;
    }

    private static String required(String value, String field, int maxLength) {
        if (value == null || value.isBlank() || value.codePointCount(0, value.length()) > maxLength) {
            throw new IllegalArgumentException(field + " is required (maximum " + maxLength + " characters)");
        }
        return value;
    }

    public Long getId() { return id; }
    public Person withId(Long id) {
        return new Person(id, firstName, lastName, street, postalCode, city, birthday);
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStreet() { return street; }
    public String getPostalCode() { return postalCode; }
    public String getCity() { return city; }
    public LocalDate getBirthday() { return birthday; }
}
