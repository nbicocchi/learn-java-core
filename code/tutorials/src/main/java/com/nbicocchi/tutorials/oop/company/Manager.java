package com.nbicocchi.tutorials.oop.company;

import java.time.LocalDate;

public class Manager extends Employee {
    protected boolean smile;

    public Manager(String name, LocalDate birthDay, String address, LocalDate startDate, Long salary, boolean smile) {
        super(name, birthDay, address, startDate, salary);
        this.smile = smile;
    }

    // getters and setters here
}
