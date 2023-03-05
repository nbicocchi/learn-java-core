package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedArrayTest {
    EnhancedArray ea;
    @BeforeEach
    void setUp() {
        ea = new EnhancedArray(5);
    }

    @Test
    void size() {
        assertEquals(5, ea.size());
    }

    @Test
    void getSet() {
        ea.set(1, 1);
        ea.set(2, 2);
        assertEquals(0, ea.get(0));
        assertEquals(1, ea.get(1));
        assertEquals(2, ea.get(2));
    }

    @Test
    void contains() {
        ea.set(1, 1);
        ea.set(2, 2);
        assertTrue(ea.contains(0));
        assertTrue(ea.contains(1));
        assertTrue(ea.contains(2));
        assertFalse(ea.contains(7));
    }

    @Test
    void resetZero() {
        ea.set(1, 1);
        ea.set(2, 2);
        ea.resetZero();
        assertArrayEquals(new int[]{0,0,0,0,0}, ea.toArray());
    }

    @Test
    void toArray() {
        assertArrayEquals(new int[]{0,0,0,0,0}, ea.toArray());
    }
}