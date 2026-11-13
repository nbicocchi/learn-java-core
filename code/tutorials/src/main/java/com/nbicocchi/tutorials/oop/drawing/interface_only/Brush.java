package com.nbicocchi.tutorials.oop.drawing.interface_only;

import com.nbicocchi.tutorials.oop.drawing.Curve;

import java.awt.*;

public class Brush implements DrawingTool {
    Color color;

    public Brush(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String draw(Curve curve) {
        return "i'm a brush drawing " + curve.draw();
    }
}
