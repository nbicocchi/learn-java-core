package com.nbicocchi.exercises.oop.shape;

public interface Resizable {

    /**
     * Resizes the object.
     * @param scale the scale of the resize. For example, 2 means that both width and height of the object have to
     * doubled.
     */
    void resize(double scale);
}
