package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreetTest {
    @Test
    public void insertCar() {
        Street s = new Street(10);
        s.insertCar(3);
        assertEquals(1, s.getCars()[3]);
    }

    @Test
    public void getCarsByPosition() {
        Street s = new Street(10);
        s.insertCar(3);
        assertEquals(1, s.getCarsByPosition(3));
    }

    @Test
    public void progress() {
        Street s = new Street(10);
        s.insertCar(3);
        s.progress();
        assertEquals(0, s.getCarsByPosition(3));
        assertEquals(1, s.getCarsByPosition(4));
        assertEquals(0, s.getCarsByPosition(0));
    }
}