package com.nbicocchi.exercises.oop.reverse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverserSlowTest {
    @Test
    void reverse() {
        Reverser r = new ReverserSlow();
        assertEquals("!dlroW olleH", r.reverse("Hello World!"));
    }
}