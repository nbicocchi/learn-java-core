package com.nbicocchi.exercises.collections;

import java.util.Arrays;

public class CheckAnagramsArr {
    public static int[] CountCharacters(String s) {
        int[] alpha = new int[26];

        for (char c : s.toCharArray()) {
            alpha[c - 'a']++;
        }
        return alpha;
    }
    public static boolean areAnagrams(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        int[] firstString = CountCharacters(first);
        int[] secondString = CountCharacters(second);

        return Arrays.equals(firstString, secondString);
    }
}
