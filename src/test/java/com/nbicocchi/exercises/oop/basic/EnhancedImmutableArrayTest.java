package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedImmutableArrayTest {
    EnhancedImmutableArray immutableArray;
    @BeforeEach
    void setUp() {
        immutableArray = new EnhancedImmutableArray(new int[]{0,1,2,3,4});
    }

    @Test
    void size() {
        assertEquals(5, immutableArray.size());
    }

    @Test
    void get() {
        assertEquals(0, immutableArray.get(0));
        assertEquals(1, immutableArray.get(1));
        assertEquals(2, immutableArray.get(2));
    }

    @Test
    void contains() {
        assertTrue(immutableArray.contains(0));
        assertTrue(immutableArray.contains(1));
        assertTrue(immutableArray.contains(2));
        assertFalse(immutableArray.contains(7));
    }

    @Test
    void toArray() {
        assertArrayEquals(new int[]{0,1,2,3,4}, immutableArray.toArray());
    }
}