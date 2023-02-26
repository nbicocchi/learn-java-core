package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolygonTest {
    @Test
    public void getVerticesNumber() {
        Point[] vertices = {new Point(0, 0), new Point(10, 0), new Point(10, 10)};
        com.nbicocchi.exercises.oop.basic.Polygon p = new com.nbicocchi.exercises.oop.basic.Polygon(vertices);
        assertEquals(3, p.getVerticesNumber());
    }

    @Test
    public void getVertices() {
        Point[] vertices = {new Point(0, 0), new Point(10, 0), new Point(10, 10)};
        com.nbicocchi.exercises.oop.basic.Polygon p = new com.nbicocchi.exercises.oop.basic.Polygon(vertices);
        assertEquals(new Point(0, 0), p.getVertices()[0]);
        assertEquals(new Point(10, 0), p.getVertices()[1]);
        assertEquals(new Point(10, 10), p.getVertices()[2]);
    }

    @Test
    public void setVertices() {
        Point[] vertices = {new Point(0, 0), new Point(10, 0), new Point(10, 10)};
        com.nbicocchi.exercises.oop.basic.Polygon p = new com.nbicocchi.exercises.oop.basic.Polygon(vertices);
        Point[] verticesNews = {new Point(1, 1), new Point(2, 2), new Point(3, 3)};
        p.setVertices(verticesNews);
        assertEquals(new Point(1, 1), p.getVertices()[0]);
        assertEquals(new Point(2, 2), p.getVertices()[1]);
        assertEquals(new Point(3, 3), p.getVertices()[2]);
    }

    @Test
    public void move() {
        Point[] vertices = {new Point(0, 0), new Point(10, 0), new Point(10, 10)};
        com.nbicocchi.exercises.oop.basic.Polygon p = new com.nbicocchi.exercises.oop.basic.Polygon(vertices);
        p.move(new Point(-5, -5));
        assertEquals(new Point(-5, -5), p.getVertices()[0]);
        assertEquals(new Point(5, -5), p.getVertices()[1]);
        assertEquals(new Point(5, 5), p.getVertices()[2]);
    }

    @Test
    public void getArea() {
        Point[] vertices = {new Point(0, 0), new Point(10, 0), new Point(10, 10)};
        com.nbicocchi.exercises.oop.basic.Polygon p = new Polygon(vertices);
        assertEquals(50.0, p.getArea(), 0.01);
    }
}