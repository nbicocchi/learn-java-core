package com.nbicocchi.exercises.arrays;

public class DivideArray {
    public static double[] divideArray(double[] v, double factor) {
        double[] out = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            out[i] = v[i] / factor;
        }
        return out;
    }
}
