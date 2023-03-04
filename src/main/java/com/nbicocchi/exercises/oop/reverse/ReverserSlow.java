package com.nbicocchi.exercises.oop.reverse;

public class ReverserSlow implements Reverser {
    @Override
    public String reverse(String s) {
        StringBuilder output = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            output.append(s.charAt(i));
        }
        return output.toString();
    }
}
