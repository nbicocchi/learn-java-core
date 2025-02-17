package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveFirstLastTest {
    @Test
    public void removeFirstLast() {
        assertEquals("Google", RemoveFirstLast.removeFirstLast("Google"));
        assertEquals("oogl", RemoveFirstLast.removeFirstLast("eoogle"));
        assertEquals("", RemoveFirstLast.removeFirstLast("FF"));
        assertEquals("F", RemoveFirstLast.removeFirstLast("F"));
    }
}
