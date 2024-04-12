package com.nbicocchi.exercises.strings;

public class RemoveFirstTwoCharsIf {
    public static String removeFirstTwoCharsIf(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == 'H' || string.charAt(i) == 'e' || i > 1) {
                sb.append(string.charAt(i));
            }
        }
        return sb.toString();
    }
}
