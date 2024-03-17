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
        circle = new Circle(new Point(5, 5), 10);
    }

    @Test
    void getArea() {
        assertEquals(314.1592653589793, circle.getArea(), DELTA);
    }

    @Test
    void setCenter(){
        circle.setCenter(new Point(6,6));
        assertEquals(circle.getCenter(), new Point(6,6));
        circle.setCenter(new Point(5,5));
    }
    @Test
    void getPerimeter() {
        assertEquals(62.83185307179586, circle.getPerimeter(), DELTA);
    }

    @Test
    void getRadius(){
        assertEquals(circle.getRadius(), 10);
    }

    @Test
    void setRadius(){
        circle.setRadius(20);
        assertEquals(circle.getRadius(), 20);
        circle.setRadius(10);
    }
    @Test
    void contains() {
        assertTrue(circle.contains(new Point(0, 0)));
        assertTrue(circle.contains(new Point(10, 10)));
        assertFalse(circle.contains(new Point(15, 15)));
        assertFalse(circle.contains(new Point(-5, -5)));
    }

    @Test
    void translate() {
        circle.translate(-10, -10);
        assertEquals(-5, circle.getCenter().getX());
        assertEquals(-5, circle.getCenter().getY());
    }

    @Test
    void testToString(){
        assertEquals("Circle{center=java.awt.Point[x=5,y=5], radius=10}", circle.toString());
    }

}