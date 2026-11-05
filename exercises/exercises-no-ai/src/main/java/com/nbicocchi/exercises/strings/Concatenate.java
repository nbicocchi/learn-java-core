package com.nbicocchi.exercises.strings;

public class Concatenate {
    public static String concatenate(String[] strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }
}
