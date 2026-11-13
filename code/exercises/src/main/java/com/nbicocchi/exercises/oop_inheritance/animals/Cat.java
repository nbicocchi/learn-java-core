package com.nbicocchi.exercises.oop_inheritance.animals;

public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "meow";
    }
}
