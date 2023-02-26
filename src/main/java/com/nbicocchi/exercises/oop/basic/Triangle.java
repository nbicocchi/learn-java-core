package com.nbicocchi.exercises.oop.basic;

/**
 * Write a class representing an isosceles triangle
 * Methods:
 * double getArea()
 * double getPerimeter()
 *
 * @author Nicola Bicocchi
 */
public class Triangle {
    double width;
    double height;

    public Triangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height / 2.0;
    }

    public double getPerimeter() {
        return width + 2 * Math.hypot(height, width / 2);
    }

    @Override
    public String toString() {
        return "Triangle{" + "width=" + width + ", height=" + height + '}';
    }
}
