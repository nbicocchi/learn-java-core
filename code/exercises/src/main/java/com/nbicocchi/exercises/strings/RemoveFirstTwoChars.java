package com.nbicocchi.exercises.strings;

public class RemoveFirstTwoChars {
    public static String removeFirstTwoChars(String s) {
        if (s.length() < 3) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
