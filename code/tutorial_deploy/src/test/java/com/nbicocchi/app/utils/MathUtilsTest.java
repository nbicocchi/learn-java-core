package com.nbicocchi.app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MathUtilsTest {

    @Test
    void testAdd() {
        assertEquals(5, MathUtils.add(2, 3));
        assertEquals(0, MathUtils.add(-2, 2));
        assertEquals(-5, MathUtils.add(-2, -3));
    }

    @Test
    void testIsEven() {
        assertTrue(MathUtils.isEven(4));
        assertFalse(MathUtils.isEven(5));
        assertTrue(MathUtils.isEven(0));
    }
}