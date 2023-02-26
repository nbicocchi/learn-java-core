package com.nbicocchi.exercises.strings;

public class Reverse {
    public static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
        // more compact version
        // return new StringBuilder(s).reverse().toString();
    }
}
