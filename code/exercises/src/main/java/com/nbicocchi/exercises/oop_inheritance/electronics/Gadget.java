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

    public double getBatteryLife() {
        return batteryLife;
    }

    public String getStatus() {
        return "Gadget: " + brand + ", battery life: " + batteryLife + " hours";
    }
}
