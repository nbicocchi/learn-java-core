package com.nbicocchi.exercises.oop_inheritance.shapes;

public class App {
    public static void main(String[] args) {
        // Array di Shape
        Shape[] shapes = new Shape[4];
        shapes[0] = new Circle("Red", 2.5);
        shapes[1] = new Rectangle("Blue", 3.0, 4.0);
        shapes[2] = new Circle("Green", 1.2);
        shapes[3] = new Rectangle("Yellow", 5.0, 2.0);

        // Polymorphism: chiamiamo i metodi tramite il tipo base Shape
        for (Shape s : shapes) {
            System.out.println(s.getDescription() + " | Area: " + s.getArea());
        }
    }
}
