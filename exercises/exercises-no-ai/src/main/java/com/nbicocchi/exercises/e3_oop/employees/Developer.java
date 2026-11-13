package com.nbicocchi.exercises.e3_oop.employees;

public class Developer extends Employee {
    private String programmingLanguage;

    public Developer(String name, double salary, String programmingLanguage) {
        super(name, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public String getDetails() {
        return "Developer{name='" + name + "', salary=" + salary + ", language='" + programmingLanguage + "'}";
    }
}
