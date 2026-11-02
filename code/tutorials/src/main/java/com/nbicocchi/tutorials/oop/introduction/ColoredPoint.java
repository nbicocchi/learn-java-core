package com.nbicocchi.tutorials.oop.introduction;

public class ColoredPoint extends Point {
    private final String color;

    public ColoredPoint(double x, double y, String color) {
        super(x, y);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " point");
    }
}
