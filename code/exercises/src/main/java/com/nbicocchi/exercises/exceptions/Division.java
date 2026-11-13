package com.nbicocchi.exercises.exceptions;

public class Division {
    public static double division(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException();
        }
        return a / b;
    }
}
