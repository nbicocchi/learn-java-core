package com.nbicocchi.tutorials.oop;

import java.util.ArrayList;

public class Shapes {
    private interface Shape {
        double getWidth();
        double getHeight();
        String draw();
    }

    private static abstract class AbstractShape implements Shape {
        double width;
        double height;

        public AbstractShape(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double getWidth() {
            return width;
        }

        @Override
        public double getHeight() {
            return height;
        }
    }

    private static class Circle extends AbstractShape {

        public Circle(double width, double height) {
            super(width, height);
        }

        @Override
        public String draw() {
            return "I'm a Circle";
        }
    }

    private static class Rectangle extends AbstractShape {

        public Rectangle(double width, double height) {
            super(width, height);
        }

        @Override
        public String draw() {
            return "I'm a Rectangle";
        }
    }

    public static void drawShapes(ArrayList<Shape> shapes) {
        for (Shape s : shapes) {
            System.out.println(s.draw());
        }
    }

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(100, 100));
        shapes.add(new Rectangle(200, 150));
        drawShapes(shapes);
    }
}
