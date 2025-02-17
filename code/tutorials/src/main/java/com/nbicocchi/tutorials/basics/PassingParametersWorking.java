package com.nbicocchi.tutorials.basics;

import java.awt.*;

public class PassingParametersWorking {
    /**
     * Swaps the coordinates of two Points
     * @param p1 The first point
     * @param p2 The second point
     */
    public static void swap(Point p1, Point p2) {
        Point tmp = new Point(p1);
        p1.setLocation(p2);
        p2.setLocation(tmp);
    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);

        System.out.println(p1);  // 0, 0
        System.out.println(p2);  // 10, 10
        swap(p1, p2);
        System.out.println(p1);  // 0, 0
        System.out.println(p2);  // 10, 10
    }
}