package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveFirstTwoCharsIfTest {
    @Test
    public void removeFirstTwoCharsIf() {
        assertEquals("Hello World!", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("Hello World!"));
        assertEquals("He", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("He"));
        assertEquals("H", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("Ho"));
        assertEquals("", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("No"));
        assertEquals("", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("Y"));
    }
}
