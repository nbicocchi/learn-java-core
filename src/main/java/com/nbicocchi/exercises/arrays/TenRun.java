package com.nbicocchi.exercises.arrays;

public class TenRun {
    public static int[] tenRun(int[] v) {
        int[] dst = new int[v.length];
        boolean foundFirst = false;
        int found = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] % 10 == 0) {
                foundFirst = true;
                found = v[i];
            }
            dst[i] = foundFirst ? found : v[i];
        }
        return dst;
    }
}
