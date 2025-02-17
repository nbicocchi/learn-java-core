package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolygonTest {
    public static final double DELTA = 1e-06;
    Polygon square;
    Polygon triangle;
    Polygon oddlyPoly;
    
    @BeforeEach
    void setUp() {
        square = new Polygon(new Point2D.Double[]{new Point2D.Double(0,0), new Point2D.Double(0, 10), new Point2D.Double(10,10), new Point2D.Double(10, 0)});
        triangle = new Polygon(new Point2D.Double[]{new Point2D.Double(0,0), new Point2D.Double(10, 0), new Point2D.Double(5,10)});
        oddlyPoly = new Polygon(new Point2D.Double[]{
                        new Point2D.Double(0,0), new Point2D.Double(0,2), new Point2D.Double(1,2), new Point2D.Double(1,6),
                        new Point2D.Double(0,6), new Point2D.Double(0,8), new Point2D.Double(3,8), new Point2D.Double(3,6),
                        new Point2D.Double(5,6), new Point2D.Double(5,8), new Point2D.Double(8,8), new Point2D.Double(8,6),
                        new Point2D.Double(7,6), new Point2D.Double(7,2), new Point2D.Double(8,2), new Point2D.Double(8,0),
                        new Point2D.Double(5,0), new Point2D.Double(5,2), new Point2D.Double(3,2), new Point2D.Double(3,0)});
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
