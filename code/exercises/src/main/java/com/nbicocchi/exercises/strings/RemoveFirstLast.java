package com.nbicocchi.exercises.strings;

public class RemoveFirstLast {
    public static String removeFirstLast(String string) {
        if (string.length() > 1) {
            char first = string.charAt(0);
            char last = string.charAt(string.length() - 1);
            if (first == last) {
                string = string.substring(1, string.length() - 1);
            }
            // compact version
            // return (s.charAt(0) == s.charAt(s.length() - 1)) ? s.substring(1, s.length() - 1) : s;
        }
        return string;
    }
}
