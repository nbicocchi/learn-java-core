package com.nbicocchi.exercises.arrays;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class FillArray {
    public static double[] fillArray(int size, double value, boolean addNoise) {
        double[] dst = new double[size];
        Arrays.fill(dst, value);
        if (addNoise) {
            RandomGenerator rnd = RandomGenerator.getDefault();
            for (int i = 0; i < dst.length; i++) {
                dst[i] += (rnd.nextDouble(value * 0.1) - value * 0.05);
            }
        }
        return dst;
    }
}
