package com.nbicocchi.tutorials.basics;

public class IntegerCaching {
    public static void main(String[] args) {
        for (int i = -130; i <= 130; i++) {
            System.out.println(i + " --> " + (Integer.valueOf(i) == Integer.valueOf(i)));
        }
    }
}
