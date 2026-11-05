package com.nbicocchi.exercises.strings;

public class Palindrome {
    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        String reversedString = new StringBuilder(s).reverse().toString();
        return reversedString.equalsIgnoreCase(s);
    }
}
