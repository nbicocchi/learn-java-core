package com.nbicocchi.exercises.oop.basic;

import java.awt.*;
import java.util.Arrays;

/**
 * A class representing an irregular polygon on a 2D plane capable of calculating its perimeter and area.
 */
public class Polygon {
    final Point[] vertices;

    /**
     * Constructs a new polygon from an array of points representing its vertices
     * @param vertices the Point array representing the vertices
     */
    public Polygon(Point[] vertices) {
        this.vertices = vertices;
    }

    /**
     * Returns the number of vertices
     * @return the number of vertices
     */
    public int getVerticesCount() {
        return vertices.length;
    }

    /**
     * Returns the perimeter of the polygon
     * @return the perimeter of the polygon
     */
    public double getPerimeter() {
        return 0.0;
    }

    /**
     * Returns the area of the polygon
     * @return the area of the polygon
     * @see <a></href="https://arachnoid.com/area_irregular_polygon/index.html></a>
     */
    public double getArea() {
        double sum = 0;
        for (int i = 0; i < vertices.length; i++) {
            int next = (i + 1) % vertices.length;
            sum += (vertices[i].x * vertices[next].y) - (vertices[i].y * vertices[next].x);
        }
        return sum / 2.0;
    }

    /**
     * Moves the polygon by translating all its vertices
     * @param movement a Point representing the movement vector
     */
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
