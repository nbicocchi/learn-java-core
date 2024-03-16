package com.nbicocchi.exercises.oop.basic;

import java.awt.*;

/**
 * A class representing a circle on a 2D plane.
 */
public class Circle {
    private Point center;
    private int radius;

    /**
     * Constructs and initializes a new circle with a specified center and radius
     * @param center A point representing the center of the circle
     * @param radius The radius of the circle
     */
    public Circle(Point center, int radius) {
        this.center = center;
        setRadius(radius);
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
        if(radius > 0) {
            this.radius = radius;
        }
        else {
            System.out.println("The radius is 0 or less, so the circumference is degenerate");
        }
    }
    /**
     * Returns the area of the circle
     * @return the area of the circle
     */
    public double getArea() {
        return Math.PI * radius * radius;
    }
    /**
     * Returns the perimeter of the circle
     * @return the perimeter of the circle
     */
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    /**
     * Verifies if a point is inside the circle
     * @param point the point to be verified
     * @return true if the point is inside the circle
     */
    public boolean contains(Point point) {
        if(point.distance(center) <= this.radius) {
            return true;
        }
        return false;
    }
    /**
     * Moves the circle of a 2D plane
     * @param dx the X movement
     * @param dy the Y movement
     */
    public void translate(int dx, int dy) {
        center.setLocation(dx,dy);
    }

    @Override
    public String toString() {
        return "Circle{" + "center=" + center + ", radius=" + radius + '}';
    }
}
