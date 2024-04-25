package com.nbicocchi.exercises.oop.reverse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverserAnonymousTest {
    @Test
    void reverseCharAtUsingStringBuilder() {
        Reverser r = new Reverser() {
            @Override
            public String reverse(String s) {
                return new StringBuilder(s).reverse().toString();
            }
        };
        assertEquals("!dlroW olleH", r.reverse("Hello World!"));
    }
}