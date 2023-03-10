package com.nbicocchi.exercises.arrays;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class FillArray {
    public static double[] fillArray(int size, double value, boolean randomize) {
        double[] dst = new double[size];
        Arrays.fill(dst, value);
        if (randomize) {
            RandomGenerator rnd = RandomGenerator.getDefault();
            for (int i = 0; i < dst.length; i++) {
                dst[i] += (rnd.nextDouble() - 0.5);
            }
        }
        return dst;
    }
}
