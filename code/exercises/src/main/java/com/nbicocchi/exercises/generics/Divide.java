package com.nbicocchi.exercises.generics;

public class Divide {
    public static <T extends Number> double divide(T a, T b) {
        return a.doubleValue() / b.doubleValue();
    }
}
