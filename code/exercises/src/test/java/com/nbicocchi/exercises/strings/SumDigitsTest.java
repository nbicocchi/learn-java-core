package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumDigitsTest {
    @Test
    void sumDigits() {
        assertEquals(6, SumDigits.sumDigits("aa1bc2d3"));
        assertEquals(8, SumDigits.sumDigits("aa11b33"));
        assertEquals(0, SumDigits.sumDigits("Chocolate"));
    }
}