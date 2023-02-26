package com.nbicocchi.exercises.oop.amphibious;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmphibiousTest {
    @Test
    void sail() {
        Amphibious a = new Amphibious();
        assertEquals("Amphibious sailing...", a.sail());
        assertEquals("Amphibious sailing...", ((WaterVehicle) a).sail());
    }

    @Test
    void run() {
        Amphibious a = new Amphibious();
        assertEquals("Amphibious running...", a.run());
        assertEquals("Amphibious running...", ((LandVehicle) a).run());
    }
}