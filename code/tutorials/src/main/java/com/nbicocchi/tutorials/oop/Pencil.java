package com.nbicocchi.tutorials.oop;

public class Pencil implements DrawingTool {
    @Override
    public String draw(Curve curve) {
        return "Pencil drawing a " + curve.draw();
    }
}
