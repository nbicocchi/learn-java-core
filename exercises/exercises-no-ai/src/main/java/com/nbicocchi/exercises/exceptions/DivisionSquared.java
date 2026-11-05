package com.nbicocchi.exercises.exceptions;

public class DivisionSquared {
    public static int divisionSquared(int a, int b) {
        try {
            return (a / b) * (a / b);
        } catch (ArithmeticException e) {
            return 0;
        }
    }
}
