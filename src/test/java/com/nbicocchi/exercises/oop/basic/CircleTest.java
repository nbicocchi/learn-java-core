package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    public static final double DELTA = 1e-06;
    Circle circle;

    @BeforeEach
    void setUp() {
        circle = new Circle(new Point(0, 0), 10);
    }

    @Test
    void getArea() {
        assertEquals(314.1592653589793, circle.getArea(), DELTA);
    }

    @Test
    void getPerimeter() {
        assertEquals(62.83185307179586, circle.getPerimeter(), DELTA);
    }

    @Test
    void contains() {
        assertTrue(circle.contains(new Point(0, 0)));
        assertFalse(circle.contains(new Point(10, 10)));
    }

    @Test
    void translate() {
        circle.translate(10, 10);
        assertEquals(10, circle.getCenter().getX());
        assertEquals(10, circle.getCenter().getY());
    }
}