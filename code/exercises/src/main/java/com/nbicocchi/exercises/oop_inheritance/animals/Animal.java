package com.nbicocchi.exercises.oop_inheritance.animals;

public class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String makeSound() {
        return "generic";
    }
}
