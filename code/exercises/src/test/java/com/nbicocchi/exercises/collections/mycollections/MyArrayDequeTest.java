package com.nbicocchi.exercises.collections.mycollections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayDequeTest {
    MyArrayDeque deque;

    @BeforeEach
    void setUp() {
        deque = new MyArrayDeque();
    }

    @Test
    void addFirst() {
        deque.addFirst("nicola");
        deque.addFirst("giovanni");
        assertEquals(2, deque.size());
        assertEquals("giovanni", deque.removeFirst());
    }

    @Test
    void removeFirst() {
        deque.addFirst("nicola");
        deque.addFirst("marco");
        deque.addFirst("giovanni");
        assertEquals("giovanni", deque.removeFirst());
    }

    @Test
    void addLast() {
        deque.addLast("nicola");
        deque.addLast("giovanni");
        assertEquals(2, deque.size());
        assertEquals("giovanni", deque.removeLast());
    }

    @Test
    void removeLast() {
        deque.addFirst("nicola");
        deque.addFirst("marco");
        deque.addFirst("giovanni");
        assertEquals("nicola", deque.removeLast());
    }
}