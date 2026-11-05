package com.nbicocchi.exercises.strings;

public class RemoveFirstLast {
    public static String removeFirstLast(String s) {
        if (s.length() < 2) {
            return s;
        }

        char first = s.charAt(0);
        char last = s.charAt(s.length() - 1);
        if (first == last) {
            return s.substring(1, s.length() - 1);
        }
        return s;
        // compact version
        // return (s.charAt(0) == s.charAt(s.length() - 1)) ? s.substring(1, s.length() - 1) : s;
    }
}
