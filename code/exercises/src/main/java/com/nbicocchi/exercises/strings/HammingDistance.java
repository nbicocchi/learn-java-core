package com.nbicocchi.exercises.strings;

public class HammingDistance {

    /**
     * Calculate the hamming distance between two strings of equal length
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return {@code int} hamming distance
     * @throws IllegalArgumentException
     */
    public static int hammingDistance(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }

        int counter = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                counter++;
            }
        }
        return counter;
    }
}
