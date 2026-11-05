package com.nbicocchi.exercises.arrays;

public class CopyArray {
    public static double[] copyArray(double[] src) {
        double[] dst = new double[src.length];
        System.arraycopy(src, 0, dst, 0, src.length);
        return dst;
    }
}
