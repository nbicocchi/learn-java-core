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

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public String getCapacityInfo() {
        return "Transport{name='" + name + "', maxPassengers=" + maxPassengers + "}";
    }

    @Override
    public String toString() {
        return getCapacityInfo();
    }
}