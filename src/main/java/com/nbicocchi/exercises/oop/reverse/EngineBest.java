package com.nbicocchi.exercises.oop.reverse;

public class EngineBest implements Reverser {
    @Override
    public String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
