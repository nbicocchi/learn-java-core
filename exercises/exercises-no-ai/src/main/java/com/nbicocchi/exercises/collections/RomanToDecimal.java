package com.nbicocchi.exercises.collections;

import java.util.Map;

public class RomanToDecimal {
    private static Map<Character, Integer> conversionMap() {
        return Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        );
    }
    public static int romanToDecimal(String s) {
        Map<Character, Integer> conversionMap = conversionMap();

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i < s.length() - 1) && conversionMap.get(s.charAt(i)) < conversionMap.get(s.charAt(i + 1))) {
                result -= conversionMap.get(s.charAt(i));
            } else {
                result += conversionMap.get(s.charAt(i));
            }
        }
        return result;
    }
}
