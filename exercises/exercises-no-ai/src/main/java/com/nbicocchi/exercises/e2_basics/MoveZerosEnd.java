package com.nbicocchi.exercises.e2_basics;

public class MoveZerosEnd {
    public static int[] moveZerosEnd(int[] v) {
        int[] output = new int[v.length];
        int j = 0;

        for (int k : v) {
            if (k != 0) {
                output[j++] = k;
            }
        }

        return output;
    }
}
