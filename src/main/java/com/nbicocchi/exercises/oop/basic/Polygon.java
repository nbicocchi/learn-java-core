package com.nbicocchi.exercises.oop.basic;

import java.awt.*;
import java.util.Arrays;

/**
 * Write a class named Polygon representing an irregular polygon.
 * Internally, the class represents an irregular polygon as an array of 2d points (vertices).
 * Supported methods:
 * getters and setters
 * int getVerticesNumber() - returns the number of vertices
 * void move(Point movement) - moves the polygon as specified by movement (treated as a vector)
 * double getArea() - returns the area of the polygon, computed using the formula that can be found here
 * (<a href="https://arachnoid.com/area_irregular_polygon/index.html">...</a>)
 *
 * @author Nicola Bicocchi
 */
public class Polygon {
    Point[] vertices;

    public Polygon(Point[] vertices) {
        this.vertices = vertices;
    }

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }

    public int getVerticesNumber() {
        return vertices.length;
    }

    public double getArea() {
        double sum = 0;
        for (int i = 0; i < vertices.length; i++) {
            int next = (i + 1) % vertices.length;
            sum += (vertices[i].x * vertices[next].y) - (vertices[i].y * vertices[next].x);
        }
        return sum / 2.0;
    }

    public void move(Point movement) {
        for (Point vertex : vertices) {
            vertex.translate(movement.x, movement.y);
        }
    }

    @Override
    public String toString() {
        return "Polygon{" + "vertices=" + Arrays.toString(vertices) + '}';
    }
}
