package com.nbicocchi.exercises.oop_inheritance.employees;

public class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Base method, can be overridden
    public String getDetails() {
        return "Employee: " + name + ", salary: " + salary;
    }
}
