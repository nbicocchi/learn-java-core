package com.nbicocchi.exercises.oop.shape;

import java.awt.*;

public class Circle extends AbstractShape {
    Point center;
    double radius;

    public Circle(String id, String color, Point center, double radius) {
        super(id, color);
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getPerimeter() {
        return 2.0 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.pow(radius, 2) * Math.PI;
    }

    @Override
    public void move(Point movement) {
        center.translate(movement.x, movement.y);
    }

    @Override
    public void resize(double scale) {
        radius *= scale;
    }

    @Override
    public String toString() {
        return "Circle{" + "center=" + center + ", radius=" + radius + ", id='" + id + '\'' + ", color=" + color + '}';
    }
}
