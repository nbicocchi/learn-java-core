package com.nbicocchi.exercises.oop_inheritance.electronics;


public class Gadget {
    protected String brand;
    protected double batteryLife; // in hours

    public Gadget(String brand, double batteryLife) {
        this.brand = brand;
        this.batteryLife = batteryLife;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(double batteryLife) {
        this.batteryLife = batteryLife;
    }

    public String getStatus() {
        return "Gadget{brand='" + brand + "', batteryLife=" + batteryLife + "}";
    }

    @Override
    public String toString() {
        return getStatus();
    }
}
