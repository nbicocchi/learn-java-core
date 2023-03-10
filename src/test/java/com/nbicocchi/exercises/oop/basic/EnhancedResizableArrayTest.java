package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedResizableArrayTest {
    EnhancedResizableArray resizableArray;
    @BeforeEach
    void setUp() {
        resizableArray = new EnhancedResizableArray();
        resizableArray.add(0);
        resizableArray.add(1);
        resizableArray.add(2);
        resizableArray.add(3);
        resizableArray.add(4);
    }

    @Test
    void add() {
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, resizableArray.toArray());
    }

    @Test
    void remove() {
        resizableArray.remove(4);
        resizableArray.remove(0);
        assertArrayEquals(new int[]{1, 2, 3}, resizableArray.toArray());
    }

    @Test
    void get() {
        assertEquals(0, resizableArray.get(0));
        assertEquals(1, resizableArray.get(1));
        assertEquals(2, resizableArray.get(2));
    }

    @Test
    void set() {
        resizableArray.set(0, 7);
        resizableArray.set(1, 8);
        resizableArray.set(2, 9);
        assertArrayEquals(new int[]{7, 8, 9, 3, 4}, resizableArray.toArray());
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
        assertEquals(5, resizableArray.size());
    }

    @Test
    void toArray() {
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, resizableArray.toArray());
    }
}