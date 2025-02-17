package com.nbicocchi.tutorials.oop.drawing.abstract_class_only;

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
