package com.nbicocchi.exercises.arrays;

public class Splitter {
    public static int[] splitter(int input) {
        String stringNumber = Integer.toString(input);
        int length = stringNumber.length();
        int[] digits = new int[length];
        for (int i = 0; i < stringNumber.length(); i++) {
            digits[i] = Character.getNumericValue(stringNumber.charAt(i));
        }
        return digits;
    }
}
