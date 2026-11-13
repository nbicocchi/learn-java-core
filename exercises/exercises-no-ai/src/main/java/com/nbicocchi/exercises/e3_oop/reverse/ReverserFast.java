package com.nbicocchi.exercises.e3_oop.reverse;

public class ReverserFast implements Reverser {
    @Override
    public String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
