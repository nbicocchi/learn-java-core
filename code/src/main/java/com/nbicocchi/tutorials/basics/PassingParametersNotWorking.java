package com.nbicocchi.tutorials.basics;

import java.awt.*;

public class PassingParametersNotWorking {
    /**
     * Swaps the references of two Point objects.
     * This does not work because the swapped references get lost when the method terminates.
     * @param p1 The first point
     * @param p2 The second point
     */
    public static void swap(Point p1, Point p2) {
        Point tmp = p1;
        p1 = p2;
        p2 = tmp;
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