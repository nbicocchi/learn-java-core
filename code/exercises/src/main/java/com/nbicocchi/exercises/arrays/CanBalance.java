package com.nbicocchi.exercises.arrays;

public class CanBalance {
    public static int sum(int[] v, int begin, int end) {
        int sum = 0;
        for (int i = begin; i < end; i++) {
            sum += v[i];
        }
        return sum;
    }

    public static boolean canBalance(int[] v) {
        if (v.length < 2) {
            return false;
        }
        int sumFromBegin = sum(v, 0, 1);
        int sumFromEnd = sum(v, 1, v.length);
        for (int i = 1; i < v.length; i++) {
            if (sumFromBegin == sumFromEnd) {
                return true;
            }
            sumFromBegin += v[i];
            sumFromEnd -= v[i];
        }
        return false;
    }
}
