package com.nbicocchi.exercises.collections;

import java.util.Map;

public class RomanToInteger {
    public static int romanToInteger(String s) {
        Map<Character, Integer> roman = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        );

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i < s.length() - 1) && roman.get(s.charAt(i)) < roman.get(s.charAt(i + 1))) {
                result -= roman.get(s.charAt(i));
            } else {
                result += roman.get(s.charAt(i));
            }
        }
        return result;
    }
}
