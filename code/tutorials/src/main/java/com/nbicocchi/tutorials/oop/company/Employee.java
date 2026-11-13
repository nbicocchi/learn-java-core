package com.nbicocchi.tutorials.oop.company;

import java.time.LocalDate;

public class Employee extends Person {
    protected LocalDate startDate;
    protected Long salary;

    public Employee(String name, LocalDate birthDay, String address, LocalDate startDate, Long salary) {
        super(name, birthDay, address);
        this.startDate = startDate;
        this.salary = salary;
    }

    // getters and setters here
}
