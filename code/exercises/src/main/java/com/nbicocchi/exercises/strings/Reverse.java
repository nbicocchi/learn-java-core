package com.nbicocchi.exercises.strings;

public class Reverse {
    public static String reverse(String string) {
        return new StringBuilder(string).reverse().toString();
    }
}
