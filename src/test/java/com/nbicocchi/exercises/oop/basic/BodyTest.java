package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BodyTest {
    @Test
    public void setSpeed() {
        Body b = new Body(0, 0, 5, 5);
        b.setVX(10);
        b.setVY(10);
        assertEquals(10.0, b.getVX(), 0.0);
    }

    @Test
    public void progress() {
        Body b = new Body(0, 0, 3, 2);
        b.progress(10);
        assertEquals(30.0, b.getX(), 0.0);
        assertEquals(20.0, b.getY(), 0.0);
    }
}