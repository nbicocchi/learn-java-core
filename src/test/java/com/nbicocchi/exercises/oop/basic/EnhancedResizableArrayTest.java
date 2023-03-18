package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedResizableArrayTest {
    EnhancedResizableArray enhancedArray;
    @BeforeEach
    void setUp() {
        enhancedArray = new EnhancedResizableArray();
        enhancedArray.set(0, 0);
        enhancedArray.set(1, 1);
        enhancedArray.set(2, 2);
        enhancedArray.set(3, 3);
    }

    @Test
    void get() {
        assertEquals(0, enhancedArray.get(0));
        assertEquals(1, enhancedArray.get(1));
        assertEquals(2, enhancedArray.get(2));
        assertEquals(3, enhancedArray.get(3));
    }

    @Test
    void set() {
        enhancedArray.set(0, 0);
        enhancedArray.set(1, 1);
        enhancedArray.set(2, 4);
        enhancedArray.set(3, 9);
        assertArrayEquals(new int[]{0, 1, 4, 9}, enhancedArray.toArray());
        enhancedArray.set(5, 25);
        assertArrayEquals(new int[]{0, 1, 4, 9, 0, 25, 0, 0, 0, 0}, enhancedArray.toArray());

    }

    @Test
    void contains() {
        enhancedArray.set(5, 25);
        assertFalse(enhancedArray.contains(7));
        assertTrue(enhancedArray.contains(0));
        assertTrue(enhancedArray.contains(1));
        assertTrue(enhancedArray.contains(25));
    }

    @Test
    void fill() {
        enhancedArray.fill(17);
        assertArrayEquals(new int[]{17, 17, 17, 17}, enhancedArray.toArray());
    }

    @Test
    void length() {
        enhancedArray.set(5, 25);
        assertEquals(10, enhancedArray.length());
        enhancedArray.set(10, 100);
        assertEquals(20, enhancedArray.length());
    }

    @Test
    void toArray() {
        assertArrayEquals(new int[]{0, 1, 2, 3}, enhancedArray.toArray());
    }
}