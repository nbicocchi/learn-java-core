package com.nbicocchi.exercises.e3_oop.animals;

public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "meow";
    }
}
