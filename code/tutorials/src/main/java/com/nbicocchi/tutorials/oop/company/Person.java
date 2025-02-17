package com.nbicocchi.tutorials.oop.company;

import java.time.LocalDate;

public class Person {
    protected String name;
    protected LocalDate birthDay;
    protected String address;

    public Person(String name, LocalDate birthDay, String address) {
        this.name = name;
        this.birthDay = birthDay;
        this.address = address;
    }

    // getters and setters here
}
