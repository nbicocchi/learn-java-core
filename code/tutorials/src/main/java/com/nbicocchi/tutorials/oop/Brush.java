package com.nbicocchi.tutorials.oop;

public class Brush implements DrawingTool {
    @Override
    public String draw(Curve curve) {
        return "Brush drawing a " + curve.draw();
    }
}
