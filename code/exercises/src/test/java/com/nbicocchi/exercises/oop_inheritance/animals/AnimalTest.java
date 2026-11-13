package com.nbicocchi.exercises.oop_inheritance.animals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalTest {

    @Test
    void testAnimals() {
        Dog dog = new Dog("Fido");
        Cat cat = new Cat("Mittens");
        Animal animal = new Animal("Generic");

        assertEquals("woof", dog.makeSound());
        assertEquals("meow", cat.makeSound());
        assertEquals("generic", animal.makeSound());
    }
}
