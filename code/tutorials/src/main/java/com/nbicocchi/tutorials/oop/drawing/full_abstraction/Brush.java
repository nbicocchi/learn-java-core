package com.nbicocchi.tutorials.oop.drawing.full_abstraction;

import com.nbicocchi.tutorials.oop.drawing.Curve;

import java.awt.*;

public class Brush extends AbstractDrawingTool {
    public Brush(Color color) {
        super(color);
    }

    @Override
    public String draw(Curve curve) {
        return "i'm a brush drawing " + curve.draw();
    }
}
