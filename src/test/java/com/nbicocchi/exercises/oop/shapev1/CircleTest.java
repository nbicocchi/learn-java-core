package com.nbicocchi.exercises.oop.shapev1;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    @Test
    void getColor() {
        Circle s = new Circle(Color.BLACK, true, new Point(0, 0), 10);
        assertEquals(Color.BLACK, s.getColor());
    }

    @Test
    void setColor() {
        Circle s = new Circle(Color.BLACK, true, new Point(0, 0), 10);
        s.setColor(Color.YELLOW);
        assertEquals(Color.YELLOW, s.getColor());
    }

    @Test
    void isFilled() {
        Circle s = new Circle(Color.BLACK, true, new Point(0, 0), 10);
        assertTrue(s.isFilled());
    }

    @Test
    void setFilled() {
        Circle s = new Circle(Color.BLACK, true, new Point(0, 0), 10);
        s.setFilled(false);
        assertFalse(s.isFilled());
    }

    @Test
    void getPerimeter() {
        Circle s = new Circle(Color.BLACK, true, new Point(0, 0), 10);
        assertEquals(62.831853, s.getPerimeter(), 0.01);
    }

    @Test
    void getArea() {
        Circle s = new Circle(Color.BLACK, true, new Point(0, 0), 10);
        assertEquals(314.159265, s.getArea(), 0.01);
    }

    @Test
    void resize() {
        Circle s = new Circle(Color.BLACK, true, new Point(0, 0), 10);
        s.resize(2.0);
        assertEquals(1256.6370, s.getArea(), 0.01);
    }

    @Test
    void move() {
        Circle s = new Circle(Color.BLACK, true, new Point(0, 0), 10);
        s.move(new Point(1, 1));
        assertEquals(new Point(1, 1), s.getCenter());
    }
}