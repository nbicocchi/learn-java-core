package com.nbicocchi.tutorials.oop.introduction;

public class DrawingApp {
    public static void main(String[] args) {
        Point[] points = {
                new Point(0, 0),
                new ColoredPoint(3, 4, "red"),
                new LabeledPoint(5, 2, "A")
        };

        for (Point point : points) {
            point.draw();
        }
    }
}
