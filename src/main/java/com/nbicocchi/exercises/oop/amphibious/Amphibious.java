package com.nbicocchi.exercises.oop.amphibious;

public class Amphibious implements LandVehicle, WaterVehicle {
    @Override
    public String sail() {
        return "Amphibious sailing...";
    }

    @Override
    public String run() {
        return "Amphibious running...";
    }
}
