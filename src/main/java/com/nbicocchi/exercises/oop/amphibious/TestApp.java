package com.nbicocchi.exercises.oop.amphibious;

/**
 * Implement the classes described in UML inside the png file contained in this package.
 * Test them with the following main.
 *
 * @author Nicola Bicocchi
 */
public class TestApp {
    public static void main(String[] args) {
        LandVehicle l;
        WaterVehicle w;
        l = new Car();
        System.out.println(l.run());
        w = new Boat();
        System.out.println(w.sail());
        l = new Amphibious();
        System.out.println(l.run());
        w = new Amphibious();
        System.out.println(w.sail());
    }
}