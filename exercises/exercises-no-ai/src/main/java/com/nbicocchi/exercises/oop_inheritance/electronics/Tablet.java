package com.nbicocchi.exercises.oop_inheritance.electronics;

public class Tablet extends Gadget {
    private double screenSize; // in inches

    public Tablet(String brand, double batteryLife, double screenSize) {
        super(brand, batteryLife);
        this.screenSize = screenSize;
    }

    @Override
    public String getStatus() {
        return "Tablet{brand='" + brand + "', batteryLife=" + batteryLife + ", screenSize=" + screenSize + "}";
    }
}
