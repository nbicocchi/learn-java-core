package com.nbicocchi.tutorials.oop.company;

import java.time.LocalDate;

public class Programmer extends Employee {
    protected String[] programmingLanguages;

    public Programmer(String name, LocalDate birthDay, String address, LocalDate startDate, Long salary, String[] programmingLanguages) {
        super(name, birthDay, address, startDate, salary);
        this.programmingLanguages = programmingLanguages;
    }

    // getters and setters here
}
