package com.nbicocchi.exercises.arrays;

public class CanBalance {
    public static boolean canBalance(int[] v) {
        if (v.length >= 2) {
            int sumAll = 0;
            for (int k : v) {
                sumAll += k;
            }
            int sum = 0;
            for (int i = 0; i < v.length; i++) {
                sum += v[i];
                if (sum == (sumAll - sum))
                    return true;
            }
        }
        return false;
    }
}
