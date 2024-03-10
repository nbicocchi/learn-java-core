package com.nbicocchi.exercises.arrays;

public class TenRun {
    public static int[] tenRun(int[] v) {
        int multiple = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] % 10 == 0) {
                multiple = v[i];
            } else if (multiple != 0) {
                v[i] = multiple;
            }
        }
        return v;
    }
}
