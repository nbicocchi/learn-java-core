package com.nbicocchi.exercises.collections;

import java.util.HashMap;
import java.util.Map;

public class CheckAnagrams {
    /**
     * Check if two strings are anagrams or not
     *
     * @param first the first string
     * @param second the second string
     *
     * @return {@code true} if two string are anagrams, otherwise {@code false}
     */
    public static boolean areAnagrams(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        Map<Character, Integer> appearancesFirst = stringToAppearencesMap(first);
        Map<Character, Integer> appearancesSecond = stringToAppearencesMap(second);
        return appearancesFirst.equals(appearancesSecond);
    }

    private static Map<Character, Integer> stringToAppearencesMap(String string) {
        Map<Character, Integer> appearances = new HashMap<>();
        for (Character c : string.toLowerCase().toCharArray()) {
            appearances.put(c, appearances.getOrDefault(c, 0) + 1);
        }
        return appearances;
    }
}
