package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseTest {
    @Test
    public void reverseCharAt() {
        assertEquals("tset gnirtS", Reverse.reverseCharAt("String test"));
        assertEquals("eoD nhoJ", Reverse.reverseCharAt("John Doe"));
        assertEquals("!dlroW olleH", Reverse.reverseCharAt("Hello World!"));
    }

    @Test
    public void reverseStringBuilder() {
        assertEquals("tset gnirtS", Reverse.reverseStringBuilder("String test"));
        assertEquals("eoD nhoJ", Reverse.reverseStringBuilder("John Doe"));
        assertEquals("!dlroW olleH", Reverse.reverseStringBuilder("Hello World!"));
    }
}
