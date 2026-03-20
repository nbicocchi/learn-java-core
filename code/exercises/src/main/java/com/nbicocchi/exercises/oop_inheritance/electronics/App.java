package com.nbicocchi.exercises.oop_inheritance.electronics;

public class App {
    public static void main(String[] args) {
        Gadget[] gadgets = new Gadget[] {
                new Tablet("iPad", 10, 12.9),
                new Smartwatch("Apple Watch", 18, true),
                new Tablet("Samsung Galaxy Tab", 14, 11.0),
                new Smartwatch("Fitbit", 24, false)
        };

        for (Gadget g : gadgets) {
            System.out.println(g.getStatus());
        }
    }
}

