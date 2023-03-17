package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedArrayTest {
    EnhancedArray enhancedArray;
    @BeforeEach
    void setUp() {
        enhancedArray = new EnhancedArray(5);
    }

    @Test
    void length() {
        assertEquals(5, enhancedArray.length());
    }

    @Test
    void getSet() {
        enhancedArray.set(1, 1);
        enhancedArray.set(2, 2);
        assertEquals(0, enhancedArray.get(0));
        assertEquals(1, enhancedArray.get(1));
        assertEquals(2, enhancedArray.get(2));
    }

    @Test
    void contains() {
        enhancedArray.set(1, 1);
        enhancedArray.set(2, 2);
        assertTrue(enhancedArray.contains(0));
        assertTrue(enhancedArray.contains(1));
        assertTrue(enhancedArray.contains(2));
        assertFalse(enhancedArray.contains(7));
    }

    @Test
    void resetZero() {
        enhancedArray.set(1, 1);
        enhancedArray.set(2, 2);
        enhancedArray.resetZero();
        assertArrayEquals(new int[]{0,0,0,0,0}, enhancedArray.toArray());
    }

    @Test
    void toArray() {
        assertArrayEquals(new int[]{0,0,0,0,0}, enhancedArray.toArray());
    }
}