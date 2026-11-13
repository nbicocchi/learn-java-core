package com.nbicocchi.exercises.arrays;

public class Fibonacci {
    public static long[] fibonacci(int n) {
        long[] sequence;
        if (n == 1) {
            sequence = new long[]{0};
        } else if (n == 2) {
            sequence = new long[]{0, 1};
        } else {
            sequence = new long[n];
            sequence[0] = 0;
            sequence[1] = 1;
            for (int i = 2; i < n; i++) {
                sequence[i] = sequence[i - 1] + sequence[i - 2];
            }
        }
        return sequence;
    }
}
