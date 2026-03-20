package com.nbicocchi.exercises.oop_inheritance.vehicles;

public class App {
    public static void main(String[] args) {
        Transport[] transports = new Transport[] {
                new Bus("CityBus", 50, true),
                new Motorcycle("Harley", 2, true),
                new Bus("MiniBus", 20, false),
                new Motorcycle("Scooter", 1, false)
        };

        for (Transport t : transports) {
            System.out.println(t.getCapacityInfo());
        }
    }
}
