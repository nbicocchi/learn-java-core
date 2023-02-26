package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairSameTypeTest {
    @Test
    void swap() {
        PairSameType<String> p1 = new PairSameType<>("nicola", "agata");
        assertEquals("agata", p1.getSecond());
        assertEquals("nicola", p1.getFirst());
        PairSameType<Integer> p2 = new PairSameType<>(10, 20);
        assertEquals(10, p2.getFirst());
        assertEquals(20, p2.getSecond());
    }
}