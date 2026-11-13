package com.nbicocchi.exercises.oop_inheritance.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeTest {

    @Test
    void testShapes() {
        Circle circle = new Circle("Red", 2.0);
        Rectangle rectangle = new Rectangle("Blue", 3.0, 4.0);
        Shape shape = new Shape("Green");

        assertEquals(Math.PI * 2.0 * 2.0, circle.getArea());
        assertEquals(12.0, rectangle.getArea());
        assertEquals(0.0, shape.getArea());

        assertEquals("Circle{color='Red', radius=2.0}", circle.getDescription());
        assertEquals("Rectangle{color='Blue', width=3.0, height=4.0}", rectangle.getDescription());
        assertEquals("Shape{color='Green'}", shape.getDescription());
    }
}