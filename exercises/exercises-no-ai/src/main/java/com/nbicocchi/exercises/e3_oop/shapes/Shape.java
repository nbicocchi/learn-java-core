package com.nbicocchi.exercises.e3_oop.shapes;

public class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public double getArea() {
        return 0;
    }

    public String getDescription() {
        return "Shape{color='" + color + "'}";
    }
}
