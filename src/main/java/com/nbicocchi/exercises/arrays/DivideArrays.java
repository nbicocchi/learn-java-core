package com.nbicocchi.exercises.arrays;

public class DivideArrays {
    public static double[] divideArrays(double[] a, double[] b) {
        if (a.length != b.length)
            return null;
        double[] out = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = a[i] / b[i];
        }
        return out;
    }
}
