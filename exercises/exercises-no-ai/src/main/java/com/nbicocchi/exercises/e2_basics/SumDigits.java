package com.nbicocchi.exercises.e2_basics;

public class SumDigits {
    public static int sumDigits(String string) {
        int sum = 0;
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }
}
