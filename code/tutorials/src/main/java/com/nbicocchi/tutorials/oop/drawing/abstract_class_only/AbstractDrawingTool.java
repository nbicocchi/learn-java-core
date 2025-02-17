package com.nbicocchi.tutorials.oop.drawing.abstract_class_only;

import com.nbicocchi.tutorials.oop.drawing.Curve;

import java.awt.*;

public abstract class AbstractDrawingTool {
    Color color;

    public AbstractDrawingTool(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract String draw(Curve curve);
}
