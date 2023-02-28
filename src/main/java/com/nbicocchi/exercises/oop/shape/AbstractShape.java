package com.nbicocchi.exercises.oop.shape;

import java.awt.*;

public abstract class AbstractShape implements Shape {
    Color color;
    boolean filled;

    public AbstractShape(Color color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public abstract double getPerimeter();

    public abstract double getArea();

    public abstract void move(Point movement);

    public abstract void resize(double scale);
}
