package com.nbicocchi.exercises.arrays;

public class FizzBuzz {
    public static String[] fizzBuzz(int start, int end) {
        if (start >= end || start < 0) {
            return new String[]{};
        }
        String[] FizzBuzz = new String[end - start];
        for (int i = 0; i < FizzBuzz.length; i++) {
            if (start % 3 == 0 && start % 5 == 0) {
                FizzBuzz[i] = "FizzBuzz";
            } else if (start % 3 == 0) {
                FizzBuzz[i] = "Fizz";
            } else if (start % 5 == 0) {
                FizzBuzz[i] = "Buzz";
            } else {
                FizzBuzz[i] = Integer.toString(start);
            }
            start++;
        }
        return FizzBuzz;
    }
}
