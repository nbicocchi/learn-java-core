package com.nbicocchi.exercises.arrays;

public class CanBalance {
    public static boolean canBalance(int[] v) {
        int totalSum = 0;
        for (int n : v) {
            totalSum += n;
        }

        int leftSum = 0;
        for (int i = 0; i < v.length - 1; i++) {
            leftSum += v[i];
            int rightSum = totalSum - leftSum;
            if (leftSum == rightSum) {
                return true;
            }
        }

        return false;
    }
}
