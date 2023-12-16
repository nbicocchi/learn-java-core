package com.nbicocchi.exercises.collections.mycollections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {
    MyMap m;

    @BeforeEach
    void setUp() {
        m = new MyHashMap();
        m.put(1, "Nicola");
        m.put(2, "Agata");
        m.put(3, "Marzia");
    }

    @Test
    void get() {
        assertEquals("Nicola", m.get(1));
        assertEquals("Agata", m.get(2));
        assertEquals("Marzia", m.get(3));
    }

    @Test
    void put() {
        assertEquals(3, m.size());
        m.put(1, "Giorgia");
        assertEquals(3, m.size());
        m.put(66, "Giorgia");
        assertEquals(4, m.size());
    }

    @Test
    void remove() {
        assertEquals(3, m.size());
        m.remove(66);
        assertEquals(3, m.size());
        m.remove(3);
        assertEquals(2, m.size());
    }

    @Test
    void contains() {
        assertTrue(m.contains(2));
        assertFalse(m.contains(12));
    }

    @Test
    void size() {
        assertEquals(3, m.size());
    }
}