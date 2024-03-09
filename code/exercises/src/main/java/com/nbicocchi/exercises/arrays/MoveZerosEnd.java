package com.nbicocchi.exercises.arrays;

public class MoveZerosEnd {
    public static int[] moveZerosEnd(int[] v) {
        int[] output = new int[v.length];
        int i, j = 0;
        for (i = 0; i < v.length; i++) {
            if (v[i] != 0) {
                output[j++] = v[i];
            }
        }
        return output;
    }
}
