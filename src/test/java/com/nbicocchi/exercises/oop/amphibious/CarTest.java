package com.nbicocchi.exercises.oop.amphibious;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    @Test
    void run() {
        LandVehicle c = new Car();
        assertEquals("Car running...", c.run());
    }
}