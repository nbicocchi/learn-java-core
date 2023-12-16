package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseTest {
    @Test
    public void reverse() {
        assertEquals("tset gnirtS", Reverse.reverse("String test"));
        assertEquals("eoD nhoJ", Reverse.reverse("John Doe"));
        assertEquals("!dlroW olleH", Reverse.reverse("Hello World!"));
    }
}
