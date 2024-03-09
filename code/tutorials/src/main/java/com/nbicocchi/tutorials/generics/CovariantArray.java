package com.nbicocchi.tutorials.generics;

public class CovariantArray {
    public static void main(String[] args) {
        String[] strings = new String[16];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = "Hello World!";
        }
        // Compiles but fails at runtime!
        Object[] objects = strings;
        objects[0] = 1.7;
    }
}
