package com.nbicocchi.exercises.oop_inheritance.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportTest {

    @Test
    void testVehicles() {
        Bus bus = new Bus("CityBus", 50, true);
        Motorcycle moto = new Motorcycle("Harley", 2, false);
        Transport transport = new Transport("GenericTransport", 10);

        assertEquals("Bus{name='CityBus', maxPassengers=50, doubleDeck=true}", bus.getCapacityInfo());
        assertEquals("Motorcycle{name='Harley', maxPassengers=2, sidecar=false}", moto.getCapacityInfo());
        assertEquals("Transport{name='GenericTransport', maxPassengers=10}", transport.getCapacityInfo());

        assertEquals(bus.getCapacityInfo(), bus.toString());
        assertEquals(moto.getCapacityInfo(), moto.toString());
        assertEquals(transport.getCapacityInfo(), transport.toString());
    }
}