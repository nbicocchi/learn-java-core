package com.nbicocchi.exercises.oop_inheritance.employees;

public class Developer extends Employee {
    private String programmingLanguage;

    public Developer(String name, double salary, String programmingLanguage) {
        super(name, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public String getDetails() {
        return "Developer: " + name + ", salary: " + salary + ", language: " + programmingLanguage;
    }
}
