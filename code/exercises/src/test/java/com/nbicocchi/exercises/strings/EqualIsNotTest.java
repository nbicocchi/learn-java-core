package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EqualIsNotTest {
    @Test
    void equalIsNot() {
        assertFalse(EqualIsNot.equalIsNot("This is not"));
        assertTrue(EqualIsNot.equalIsNot("This is notnot"));
        assertTrue(EqualIsNot.equalIsNot("noisxxnotyynotxisi"));
        assertThrows(IllegalArgumentException.class, () -> EqualIsNot.equalIsNot(""));
    }
}