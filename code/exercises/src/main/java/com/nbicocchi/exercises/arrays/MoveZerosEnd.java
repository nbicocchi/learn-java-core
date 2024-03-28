package com.nbicocchi.exercises.arrays;

public class MoveZerosEnd {
    public static int[] moveZerosEnd(int[] v) {
        if (v == null){
            return null;
        }
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
