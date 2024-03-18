package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolygonTest {
    public static final double DELTA = 1e-06;
    Polygon square;
    Polygon triangle;
    Polygon oddlyPoly;
    
    @BeforeEach
    void setUp() {
        square = new Polygon(new Point[]{new Point(0,0), new Point(0, 10), new Point(10,10), new Point(10, 0)});
        triangle = new Polygon(new Point[]{new Point(0,0), new Point(10, 0), new Point(5,10)});
        oddlyPoly = new Polygon(new Point[]{
                        new Point(0,0), new Point(0,2), new Point(1,2), new Point(1,6),
                        new Point(0,6), new Point(0,8), new Point(3,8), new Point(3,6),
                        new Point(5,6), new Point(5,8), new Point(8,8), new Point(8,6),
                        new Point(7,6), new Point(7,2), new Point(8,2), new Point(8,0),
                        new Point(5,0), new Point(5,2), new Point(3,2), new Point(3,0)});
    }

    @Test
    void getVerticesCount() {
        assertEquals(4, square.getVerticesCount());
        assertEquals(3, triangle.getVerticesCount());
        assertEquals(20, oddlyPoly.getVerticesCount());
    }

    @Test
    void getPerimeter() {
        assertEquals(40, square.getPerimeter(), DELTA);
        assertEquals(32.3606797749979, triangle.getPerimeter(), DELTA);
        assertEquals(44, oddlyPoly.getPerimeter(), DELTA);
    }

    @Test
    void getArea() {
        assertEquals(100, square.getArea(), DELTA);
        assertEquals(50, triangle.getArea(), DELTA);
        assertEquals(48, oddlyPoly.getArea(), DELTA);
    }
}
