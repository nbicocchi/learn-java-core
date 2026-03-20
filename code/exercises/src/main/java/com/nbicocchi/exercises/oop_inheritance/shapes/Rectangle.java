package com.nbicocchi.exercises.oop_inheritance.shapes;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String getDescription() {
        return "Rectangle with color: " + color + ", width: " + width + ", height: " + height;
    }
}
