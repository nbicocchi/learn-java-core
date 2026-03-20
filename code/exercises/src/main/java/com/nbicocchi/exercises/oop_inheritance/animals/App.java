package com.nbicocchi.exercises.oop_inheritance.animals;

public class App {
    public static void main(String[] args) {
        // Array di Animal
        Animal[] animals = new Animal[3];
        animals[0] = new Animal("GenericAnimal");
        animals[1] = new Dog("Rex");
        animals[2] = new Cat("Whiskers");

        // Dimostrazione di polimorfismo
        for (Animal a : animals) {
            System.out.println(a.getName() + " says: " + a.makeSound());
        }
    }
}
