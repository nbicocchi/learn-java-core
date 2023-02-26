package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairSameTypeSwapTest {
    @Test
    void swap() {
        PairSameTypeSwap<String> p = new PairSameTypeSwap<>("nicola", "agata");
        assertEquals("agata", p.getSecond());
        assertEquals("nicola", p.getFirst());
        p.swap();
        assertEquals("nicola", p.getSecond());
        assertEquals("agata", p.getFirst());
    }
}