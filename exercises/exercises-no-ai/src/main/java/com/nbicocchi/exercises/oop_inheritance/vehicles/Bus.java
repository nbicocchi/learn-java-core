package com.nbicocchi.exercises.oop_inheritance.vehicles;

public class Bus extends Transport {
    private boolean hasDoubleDeck;

    public Bus(String name, int maxPassengers, boolean hasDoubleDeck) {
        super(name, maxPassengers);
        this.hasDoubleDeck = hasDoubleDeck;
    }

    @Override
    public String getCapacityInfo() {
        return "Bus{name='" + name + "', maxPassengers=" + maxPassengers + ", doubleDeck=" + hasDoubleDeck + "}";
    }
}