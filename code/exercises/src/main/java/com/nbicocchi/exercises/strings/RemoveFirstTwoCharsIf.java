package com.nbicocchi.exercises.strings;

public class RemoveFirstTwoCharsIf {
    public static String removeFirstTwoCharsIf(String string) {
        StringBuilder sb = new StringBuilder(string);
        if (!string.isEmpty() && string.charAt(0) != 'H') {
            sb.deleteCharAt(0);
        }
        if (string.length() > 1 && string.charAt(1) != 'e') {
            sb.deleteCharAt(1);
        }
        return sb.toString();
    }
}
