package com.nbicocchi.exercises.e3_oop.employees;

public class Manager extends Employee {
    private int teamSize;

    public Manager(String name, double salary, int teamSize) {
        super(name, salary);
        this.teamSize = teamSize;
    }

    @Override
    public String getDetails() {
        return "Manager{name='" + name + "', salary=" + salary + ", teamSize=" + teamSize + "}";
    }
}
