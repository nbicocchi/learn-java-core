package com.nbicocchi.exercises.strings;

public class RemoveFirstTwoChars {
    public static String removeFirstTwoChars(String s) {
        return new StringBuilder(s).delete(0, 2).toString();
    }
}
