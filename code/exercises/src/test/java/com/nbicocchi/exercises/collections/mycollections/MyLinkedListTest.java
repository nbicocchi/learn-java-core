package com.nbicocchi.exercises.collections.mycollections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyLinkedListTest {
    MyLinkedList l;
    @BeforeEach
    void setUp() {
        l = new MyLinkedList();
    }

    @Test
    void addFirst() {
        l.add("nicola");
        l.add("giovanni");
        assertEquals(2, l.size());
        assertEquals("nicola", l.removeFirst());
    }

    @Test
    void removeFirst() {
        l.addFirst("nicola");
        l.addFirst("marco");
        l.addFirst("giovanni");
        assertEquals("giovanni", l.removeFirst());
    }

    @Test
    void addLast() {
        l.addLast("nicola");
        l.addLast("giovanni");
        assertEquals(2, l.size());
        assertEquals("giovanni", l.removeLast());
    }

    @Test
    void removeLast() {
        l.addFirst("nicola");
        l.addFirst("marco");
        l.addFirst("giovanni");
        assertEquals("nicola", l.removeLast());
    }
}