package com.nbicocchi.exercises.arrays;

public class FizzBuzz {
    public static String[] fizzBuzz(int start, int end) {
        if (start >= end || start < 0) {
            return new String[]{};
        }

        int j = 0;
        String[] dst = new String[end - start];
        for (int i = start; i < end; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                dst[j] = "FizzBuzz";
            } else if (i % 3 == 0) {
                dst[j] = "Fizz";
            } else if (i % 5 == 0) {
                dst[j] = "Buzz";
            } else {
                dst[j] = Integer.toString(i);
            }
            j++;
        }
        return dst;
    }
}
