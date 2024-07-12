package com.nbicocchi.exercises.strings;

public class RemoveFirstTwoChars {
    public static String removeFirstTwoCharsCharAt(String string) {

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < string.length(); i++) {
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }

    public static String removeFirstTwoCharsStringBuilder(String string) {
        return new StringBuilder(string).delete(0, 2).toString();
    }
}
