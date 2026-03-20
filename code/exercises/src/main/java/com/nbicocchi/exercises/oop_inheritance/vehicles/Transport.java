package com.nbicocchi.exercises.oop_inheritance.vehicles;

public class Transport {
    protected String name;
    protected int maxPassengers;

    public Transport(String name, int maxPassengers) {
        this.name = name;
        this.maxPassengers = maxPassengers;
    }

    public String getName() {
        return name;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public String getCapacityInfo() {
        return "Transport: " + name + " can carry " + maxPassengers + " passengers";
    }
}