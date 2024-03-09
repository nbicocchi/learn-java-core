package com.nbicocchi.exercises.arrays;

import java.util.Arrays;

public class DeepCopy {
    public static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            // Arrays.copyOf() takes care of allocating the destination
            copy[i] = Arrays.copyOf(original[i], original[i].length);
            // Alternative version: System.arraycopy() requires the destination array to be allocated manually
            // copy[i] = new int[original[i].length];
            // System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }
}
