package com.nbicocchi.exercises.oop.reverse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EngineBestTest {
    @Test
    void reverse() {
        Reverser r = new EngineBest();
        assertEquals("!dlroW olleH", r.reverse("Hello World!"));
    }
}