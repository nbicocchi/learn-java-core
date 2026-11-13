package com.nbicocchi.tutorials.strings;

public class ComparingStrings {
    public static void checkStrings(String s1, String s2) {
        if (s1 == s2) {
            System.out.println("s1 and s2 point to the same object");
        } else {
            System.out.println("s1 and s2 point to different objects");
        }

        if (s1.equals(s2)) {
            System.out.println("s1 and s2 have the same content");
        } else {
            System.out.println("s1 and s2 have different contents");
        }
    }
    public static void main(String[] args) {
        checkStrings("Hello World!", "Hello World!");
        checkStrings(new String("Hello World!"), new String("Hello World!"));
        checkStrings(new String("Hello World!"), new String("Hello Mars!"));
    }
}
