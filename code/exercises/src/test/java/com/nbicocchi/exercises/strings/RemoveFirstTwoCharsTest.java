package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveFirstTwoCharsTest {
    @Test
    public void removeFirstTwoChars() {
        assertEquals("llo World!", RemoveFirstTwoChars.removeFirstTwoChars("Hello World!"));
        assertEquals("", RemoveFirstTwoChars.removeFirstTwoChars("No"));
        assertEquals("", RemoveFirstTwoChars.removeFirstTwoChars("Y"));
    }
}
