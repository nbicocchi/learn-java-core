package com.nbicocchi.exercises.oop.reverse;

public class EngineFast implements Reverser {
    @Override
    public String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
