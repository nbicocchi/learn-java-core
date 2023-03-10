package com.nbicocchi.exercises.collections.mycollections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class MyListTestBase {
    MyList l;

    @Test
    void add() {
        l.add("nicola");
        l.add("barbara", 0);
        assertEquals("barbara", l.get(0));
        assertEquals("nicola", l.get(1));
        assertEquals(2, l.size());
    }

    @Test
    void remove() {
        l.add("nicola");
        l.add("barbara");
        l.add("marzia");
        l.remove(2);
        l.remove("barbara");
        assertEquals("nicola", l.get(0));
        assertEquals(1, l.size());
    }

    @Test
    void size() {
        assertEquals(0, l.size());
        l.add("nicola");
        assertEquals(1, l.size());
        l.remove(0);
        assertEquals(0, l.size());
    }

    @Test
    void contains() {
        assertEquals(0, l.size());
        l.add("nicola");
        assertEquals(1, l.size());
        assertTrue(l.contains("nicola"));
        assertFalse(l.contains("marzia"));
    }
}