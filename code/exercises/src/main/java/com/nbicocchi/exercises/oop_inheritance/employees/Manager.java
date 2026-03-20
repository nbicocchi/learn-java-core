package com.nbicocchi.exercises.oop_inheritance.employees;

class Manager extends Employee {
    private int teamSize;

    public Manager(String name, double salary, int teamSize) {
        super(name, salary);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String getDetails() {
        return "Manager: " + name + ", salary: " + salary + ", team size: " + teamSize;
    }
}
