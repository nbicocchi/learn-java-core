package com.nbicocchi.exercises.strings;

public class RemoveFirstTwoCharsIf {
    public static String removeFirstTwoCharsIfCharAt(String string) {
        StringBuilder sb = new StringBuilder();
        if (!string.isEmpty() && string.charAt(0) == 'H') {
            sb.append('H');
        }
        if (string.length() > 1 && string.charAt(1) == 'e') {
            sb.append('e');
        }
        if (string.length() > 2) {
            sb.append(string.substring(2));
        }
        return sb.toString();
    }


    public static String removeFirstTwoCharsIfStringBuilder(String string) {
        StringBuilder sb = new StringBuilder(string);
        if (string.length() > 1 && string.charAt(1) != 'e') {
            sb.deleteCharAt(1);
        }
        if (!string.isEmpty() && string.charAt(0) != 'H') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
