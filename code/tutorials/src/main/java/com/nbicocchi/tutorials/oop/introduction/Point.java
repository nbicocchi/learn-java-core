package com.nbicocchi.tutorials.oop.introduction;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.hypot(dx, dy);
    }

    public double distance(double x, double y) {
        return distance(new Point(x, y));
    }

    public void draw() {
        System.out.println("Drawing a point");
    }
}
