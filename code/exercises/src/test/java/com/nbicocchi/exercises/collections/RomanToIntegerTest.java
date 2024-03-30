package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RomanToIntegerTest {

    @Test
    void romanToInteger() {
        assertEquals(3, RomanToInteger.romanToInteger("III"));
        assertEquals(58, RomanToInteger.romanToInteger("LVIII"));
        assertEquals(1994, RomanToInteger.romanToInteger("MCMXCIV"));
        assertEquals(3999, RomanToInteger.romanToInteger("MMMCMXCIX"));
        assertEquals(444, RomanToInteger.romanToInteger("CDXLIV"));
        assertEquals(3888, RomanToInteger.romanToInteger("MMMDCCCLXXXVIII"));
    }

}