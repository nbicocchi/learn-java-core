package com.nbicocchi.tutorials.oop.introduction;

public class LabeledPoint extends Point {
    private final String label;

    public LabeledPoint(double x, double y, String label) {
        super(x, y);
        this.label = label;
    }

    @Override
    public void draw() {
        System.out.println("Drawing point " + label);
    }
}
