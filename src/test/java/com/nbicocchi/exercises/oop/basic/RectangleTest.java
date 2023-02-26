package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {
    @Test
    void getArea() {
        Rectangle r = new Rectangle(5, 10);
        assertEquals(50, r.getArea());
    }

    @Test
    void getPerimeter() {
        Rectangle r = new Rectangle(5, 10);
        assertEquals(30, r.getPerimeter());
    }
}