package com.nbicocchi.exercises.oop_inheritance.electronics;

public class Smartwatch extends Gadget {
    private boolean supportsHeartRate;

    public Smartwatch(String brand, double batteryLife, boolean supportsHeartRate) {
        super(brand, batteryLife);
        this.supportsHeartRate = supportsHeartRate;
    }

    @Override
    public String getStatus() {
        return "Smartwatch{brand='" + brand + "', batteryLife=" + batteryLife + ", heartRate=" + supportsHeartRate + "}";
    }
}