package com.nbicocchi.exercises.oop.shape;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    public static final double DELTA = 1e-06;
    Circle circle;

    @BeforeEach
    void setUp() {
        circle = new Circle("s01", "#104050", new Point(10, 10), 10);
    }

    @Test
    void setColor() {
        assertDoesNotThrow(() -> circle.setColor("#000000"));
        assertDoesNotThrow(() -> circle.setColor("#123456"));
        assertDoesNotThrow(() -> circle.setColor("#ABCDEF"));
        assertThrows(IllegalArgumentException.class, () -> circle.setColor("#ABCDEFF"));
        assertThrows(IllegalArgumentException.class, () -> circle.setColor("#ABCDEG"));
        assertThrows(IllegalArgumentException.class, () -> circle.setColor("#ABCDE"));
    }

    @Test
    void getPerimeter() {
        assertEquals(62.83185307179586, circle.getPerimeter(), DELTA);
    }

    @Test
    void getArea() {
        assertEquals(314.1592653589793, circle.getArea(), DELTA);
    }

    @Test
    void move() {
        circle.move(new Point(-10, -10));
        assertEquals(0, circle.getCenter().getX());
        assertEquals(0, circle.getCenter().getY());
    }

    @Test
    void resize() {
        circle.resize(2);
        assertEquals(20, circle.getRadius());

    }
}