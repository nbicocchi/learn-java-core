package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualIsNotTest {
    @Test
    void equalIsNot() {
        assertTrue(EqualIsNot.equalIsNot("is not"));
        assertFalse(EqualIsNot.equalIsNot("This is not"));
        assertTrue(EqualIsNot.equalIsNot("This is notnot"));
        assertTrue(EqualIsNot.equalIsNot("noisxxnotyynotxisi"));
        assertTrue(EqualIsNot.equalIsNot(""));
    }
}