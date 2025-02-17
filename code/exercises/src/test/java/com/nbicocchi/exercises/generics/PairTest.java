package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairTest {
    @Test
    void pairTest() {
        Pair<String, String> p1 = new Pair<>("nicola", "agata");
        assertEquals("nicola", p1.getFirst());
        assertEquals("agata", p1.getSecond());
        Pair<Integer, Integer> p2 = new Pair<>(10, 20);
        assertEquals(10, p2.getFirst());
        assertEquals(20, p2.getSecond());
    }
}
