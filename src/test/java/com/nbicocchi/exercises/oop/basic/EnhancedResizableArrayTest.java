package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedResizableArrayTest {
    EnhancedResizableArray resizableArray;
    @BeforeEach
    void setUp() {
        resizableArray = new EnhancedResizableArray();
        resizableArray.set(0, 0);
        resizableArray.set(1, 1);
        resizableArray.set(2, 2);
        resizableArray.set(3, 3);
    }

    @Test
    void get() {
        assertEquals(0, resizableArray.get(0));
        assertEquals(1, resizableArray.get(1));
        assertEquals(2, resizableArray.get(2));
        assertEquals(3, resizableArray.get(3));
    }

    @Test
    void set() {
        resizableArray.set(0, 0);
        resizableArray.set(1, 1);
        resizableArray.set(2, 4);
        resizableArray.set(3, 9);
        assertArrayEquals(new int[]{0, 1, 4, 9}, resizableArray.toArray());
        resizableArray.set(4, 16);
        assertArrayEquals(new int[]{0, 1, 4, 9, 16, 0, 0, 0}, resizableArray.toArray());

    }

    @Test
    void contains() {
        assertTrue(resizableArray.contains(0));
        assertTrue(resizableArray.contains(1));
        assertTrue(resizableArray.contains(2));
        assertFalse(resizableArray.contains(7));
    }

    @Test
    void size() {
        assertEquals(4, resizableArray.length());
    }

    @Test
    void toArray() {
        assertArrayEquals(new int[]{0, 1, 2, 3}, resizableArray.toArray());
    }
}