package com.nbicocchi.exercises.strings;

public class RemoveFirstTwoCharsIf {
    public static String removeFirstTwoCharsIfCharAt(String string) {
        StringBuilder sb = new StringBuilder();
        switch (string.length()) {
            case 0 -> {
            }
            case 1 -> {
                if (string.charAt(0) == 'H')
                    sb.append("H");
            }
            case 2 -> {
                if (string.charAt(0) == 'H')
                    sb.append("H");
                if (string.charAt(1) == 'e')
                    sb.append("e");
            }
            default -> {
                if (string.charAt(0) == 'H')
                    sb.append("H");
                if (string.charAt(1) == 'e')
                    sb.append("e");
                sb.append(string.substring(2));
            }
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
