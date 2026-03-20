package com.nbicocchi.exercises.oop_inheritance.shapes;

public class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getDescription() {
        return "Circle{color='" + color + "', radius=" + radius + "}";
    }
}
