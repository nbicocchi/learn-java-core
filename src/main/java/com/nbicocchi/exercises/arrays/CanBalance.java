package com.nbicocchi.exercises.arrays;

public class CanBalance {
    public static boolean canBalance(int[] v) {
        if (v.length < 2) {
            return false;
        }
        for (int i = 1; i < v.length; i++) {
            if (sum(v, 0, i) == sum(v, i, v.length)) {
                return true;
            }
        }
        return false;
    }

    public static int sum(int[] v, int begin, int end) {
        int sum = 0;
        for (int i = begin; i < end; i++) {
            sum += v[i];
        }
        return sum;
    }
}
