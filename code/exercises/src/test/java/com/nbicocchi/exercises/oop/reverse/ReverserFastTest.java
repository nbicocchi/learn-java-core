package com.nbicocchi.exercises.oop.reverse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverserFastTest {
    @Test
    void reverseCharAtUsingStringBuilder() {
        Reverser r = new ReverserFast();
        assertEquals("!dlroW olleH", r.reverse("Hello World!"));
    }
}