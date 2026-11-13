package com.nbicocchi.exercises.oop_full.reverse;

public class ReverserSlow implements Reverser {
    @Override
    public String reverse(String s) {
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[s.length() - 1 - i] = s.charAt(i);
        }
        return String.valueOf(chars);
    }
}
