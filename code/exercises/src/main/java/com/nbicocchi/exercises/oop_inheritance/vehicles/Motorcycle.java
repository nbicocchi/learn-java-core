package com.nbicocchi.exercises.oop_inheritance.vehicles;


public class Motorcycle extends Transport {
    private boolean hasSidecar;

    public Motorcycle(String name, int maxPassengers, boolean hasSidecar) {
        super(name, maxPassengers);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public String getCapacityInfo() {
        return "Motorcycle: " + name + " can carry " + maxPassengers + " passengers"
                + (hasSidecar ? " and has a sidecar" : "");
    }
}