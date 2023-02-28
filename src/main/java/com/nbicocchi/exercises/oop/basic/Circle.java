package com.nbicocchi.exercises.oop.basic;

import java.awt.*;

/**
 * Write a Java class representing a Circle (Point center, int radius) capable of moving on a 2D plane
 * The class implements one constructor:
 * Circle(Point center, int radius)
 *
 * The class have also to implement the following methods:
 * getters and setters
 * double getArea()
 * double getPerimeter()
 * boolean contains(Point point)
 * void moveUp() (center.y += 1)
 * void moveDown() (center.y -= 1)
 * void moveLeft() (center.x -= 1)
 * void moveRight() (center.x += 1)
 * toString()
 *
 * @author Nicola Bicocchi
 */
public class Circle {
    Point center;
    int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public boolean contains(Point point) {
        return Math.hypot(point.x - center.x, point.y - center.y) < radius;
    }

    public void moveUp() {
        center.translate(0, 1);
    }

    public void moveDown() {
        center.translate(0, -1);
    }

    public void moveLeft() {
        center.translate(-1, 0);
    }

    public void moveRight() {
        center.translate(1, 0);
    }

    @Override
    public String toString() {
        return "Circle{" + "center=" + center + ", radius=" + radius + '}';
    }
}
