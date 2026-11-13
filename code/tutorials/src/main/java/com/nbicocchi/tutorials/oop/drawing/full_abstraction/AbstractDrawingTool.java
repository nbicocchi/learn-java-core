package com.nbicocchi.tutorials.oop.drawing.full_abstraction;

import java.awt.*;

public abstract class AbstractDrawingTool implements DrawingTool {
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
}
