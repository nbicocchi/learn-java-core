package com.nbicocchi.exercises.oop.shape;

import java.awt.*;

public interface Computable {
    /**
     * Computes the perimeter of the object.
     * @return the perimeter of the object.
     */
    double getPerimeter();

    /**
     * Computes the area of the object.
     * @return the area of the object.
     */
    double getArea();
}
