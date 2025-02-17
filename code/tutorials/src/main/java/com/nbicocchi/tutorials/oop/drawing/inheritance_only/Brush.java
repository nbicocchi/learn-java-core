package com.nbicocchi.tutorials.oop.drawing.inheritance_only;

import com.nbicocchi.tutorials.oop.drawing.Curve;

import java.awt.*;

public class Brush extends DrawingTool {
    public Brush(Color color) {
        super(color);
    }

    @Override
    public String draw(Curve curve) {
        return "i'm a brush drawing a " + curve.draw();
    }
}
