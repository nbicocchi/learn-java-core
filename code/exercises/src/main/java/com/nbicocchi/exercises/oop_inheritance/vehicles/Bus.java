package com.nbicocchi.exercises.oop_inheritance.vehicles;

public class Bus extends Transport {
    private boolean hasDoubleDeck;

    public Bus(String name, int maxPassengers, boolean hasDoubleDeck) {
        super(name, maxPassengers);
        this.hasDoubleDeck = hasDoubleDeck;
    }

    @Override
    public String getCapacityInfo() {
        return "Bus: " + name + " can carry " + maxPassengers + " passengers"
                + (hasDoubleDeck ? " and has a double deck" : "");
    }
}