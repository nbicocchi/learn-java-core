package com.nbicocchi.tutorials.oop.drawing.inheritance_only;

import com.nbicocchi.tutorials.oop.drawing.Curve;

import java.awt.*;

public class DrawingTool {
    Color color;

    public DrawingTool(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String draw(Curve curve) {
        return "";
    }
}
