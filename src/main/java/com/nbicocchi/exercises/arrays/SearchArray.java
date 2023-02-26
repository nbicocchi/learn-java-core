package com.nbicocchi.exercises.arrays;

import java.util.Arrays;

public class SearchArray {
    public static boolean containsBruteForce(String[] strings, String searched) {
        for (String string : strings) {
            if (string.equals(searched)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsBinary(String[] strings, String searched) {
        Arrays.sort(strings);
        return Arrays.binarySearch(strings, searched) >= 0;
    }
}
