package com.nbicocchi.exercises.arrays;

public class DivideArray {
    public static double[] divideArray(double[] in, double f) {
        double[] out = new double[in.length];
        for (int i = 0; i < in.length; i++) {
            out[i] = in[i] / f;
        }
        return out;
    }
}
