package com.nbicocchi.exercises.oop.shape;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {
    public static final double DELTA = 1e-06;
    Rectangle rectangle;

    @BeforeEach
    void setUp() {
        rectangle = new Rectangle("s01", "#104050", new Point(0, 10), new Point(10, 0));
    }

    @Test
    void getPerimeter() {
        assertEquals(40, rectangle.getPerimeter(), DELTA);
    }

    @Test
    void getArea() {
        assertEquals(100, rectangle.getArea(), DELTA);
    }

    @Test
    void move() {
        rectangle.move(new Point(-5, -5));
        assertEquals(new Point(5, -5), rectangle.getBottomRight());
        assertEquals(new Point(-5, 5), rectangle.getUpperLeft());
    }

    @Test
    void resize() {
        rectangle.resize(2);
        assertEquals(new Point(20, -10), rectangle.getBottomRight());
        assertEquals(new Point(0, 10), rectangle.getUpperLeft());
    }
}