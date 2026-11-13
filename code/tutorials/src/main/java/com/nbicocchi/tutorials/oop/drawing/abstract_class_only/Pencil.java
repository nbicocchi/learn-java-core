package com.nbicocchi.tutorials.oop.drawing.abstract_class_only;

import com.nbicocchi.tutorials.oop.drawing.Curve;

import java.awt.*;

public class Pencil extends AbstractDrawingTool {

    public Pencil(Color color) {
        super(color);
    }

    @Override
    public String draw(Curve curve) {
        return "i'm a pencil drawing " + curve.draw();
    }
}
