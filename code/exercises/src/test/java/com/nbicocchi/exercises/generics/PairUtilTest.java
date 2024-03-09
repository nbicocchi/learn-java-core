package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairUtilTest {
    @Test
    void swap() {
        Pair<Integer, String> src = new Pair<>(0, "nicola");
        Pair<String, Integer> dst = PairUtil.swap(src);
        assertEquals(0, dst.getSecond());
        assertEquals("nicola", dst.getFirst());
    }
}