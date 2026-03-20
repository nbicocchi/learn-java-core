package com.nbicocchi.exercises.oop_full.reverse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverserFastTest {
    @Test
    void reverse() {
        Reverser r = new ReverserFast();
        assertEquals("!dlroW olleH", r.reverse("Hello World!"));
    }
}