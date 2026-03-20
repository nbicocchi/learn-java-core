package com.nbicocchi.exercises.oop_inheritance.electronics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GadgetTest {
    @Test
    void testElectronics() {
        Tablet tablet = new Tablet("Apple", 10.0, 12.9);
        Smartwatch watch = new Smartwatch("Samsung", 2.0, true);
        Gadget gadget = new Gadget("GenericBrand", 5.0);

        assertEquals("Tablet{brand='Apple', batteryLife=10.0, screenSize=12.9}", tablet.getStatus());
        assertEquals("Smartwatch{brand='Samsung', batteryLife=2.0, heartRate=true}", watch.getStatus());
        assertEquals("Gadget{brand='GenericBrand', batteryLife=5.0}", gadget.getStatus());

        assertEquals(tablet.getStatus(), tablet.toString());
        assertEquals(watch.getStatus(), watch.toString());
        assertEquals(gadget.getStatus(), gadget.toString());
    }
}