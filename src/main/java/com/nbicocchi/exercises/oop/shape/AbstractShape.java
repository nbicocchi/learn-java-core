package com.nbicocchi.exercises.oop.shape;

import java.awt.*;

public abstract class AbstractShape implements Computable, Movable, Resizable {
    String id;
    String color;

    /**
     * Constructs a shape with the specified id, and color
     * @param id the id to be assigned to the shape
     * @param color the color to be assigned to the shape
     */
    public AbstractShape(String id, String color) {
        this.id = id;
        setColor(color);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (!color.matches("^#[0-9a-fA-F]{6}")) {
            throw new IllegalArgumentException("Invalid RGB Color (#RRGGBB)");
        }
        this.color = color;
    }

    public abstract double getPerimeter();

    public abstract double getArea();

    public abstract void move(Point movement);

    public abstract void resize(double scale);
}
