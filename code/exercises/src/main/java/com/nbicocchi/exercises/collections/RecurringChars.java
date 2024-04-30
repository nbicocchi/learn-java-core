package com.nbicocchi.exercises.collections;

import java.util.HashSet;
import java.util.Set;

public class RecurringChars {
    public static Set<Character> recurringChars(String string) {
        Set<Character> seen = new HashSet<>();
        Set<Character> duplicates = new HashSet<>();

        if(string.isEmpty()){
            return duplicates;
        }

        for (char c : string.toCharArray()) {
            if (!seen.contains(c)) {
                seen.add(c);
            } else {
                duplicates.add(c);
            }
        }
        return duplicates;
    }
}
