package com.nbicocchi.exercises.e3_oop.animals;

public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "woof";
    }
}
