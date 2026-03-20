package com.nbicocchi.exercises.oop_inheritance.animals;

public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "woof";
    }
}
