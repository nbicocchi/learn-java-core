package com.nbicocchi.exercises.oop.shape;

import java.awt.*;

public abstract class AbstractShape implements Computable, Movable, Resizable {
    String id;
    Color color;

    /**
     * Constructs a shape with the specified id, and color
     * @param id the id to be assigned to the shape
     * @param color the color to be assigned to the shape
     */
    public AbstractShape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract double getPerimeter();

    public abstract double getArea();

    public abstract void move(Point movement);

    public abstract void resize(double scale);
}
