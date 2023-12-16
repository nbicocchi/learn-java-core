package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveFirstTwoCharsIfTest {
    @Test
    public void removeFirstTwoCharsIf() {
        assertEquals("Hello World!", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("Hello World!"));
        assertEquals("od World!", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("Good World!"));
        assertEquals("H", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("Ho"));
        assertEquals("e", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("Ne"));
        assertEquals("", RemoveFirstTwoCharsIf.removeFirstTwoCharsIf("Y"));
    }
}
