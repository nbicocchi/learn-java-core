package com.nbicocchi.exercises.oop.shapev3;

import java.awt.*;

public interface Shape {
    double getPerimeter();

    double getArea();

    void move(Point movement);

    void resize(double scale);
}
