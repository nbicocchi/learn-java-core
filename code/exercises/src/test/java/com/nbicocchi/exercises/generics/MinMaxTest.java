package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinMaxTest {

    @Test
    void max() {
        assertEquals(5, MinMax.max(List.of(1, 2, 3, 4, 5), (o1, o2) -> o1.compareTo(o2)));
        assertEquals("zazza", MinMax.max(List.of("abaco", "barco", "zazza"), (o1, o2) -> o1.compareTo(o2)));
    }

    @Test
    void min() {
        assertEquals(1, MinMax.min(List.of(1, 2, 3, 4, 5), (o1, o2) -> o1.compareTo(o2)));
        assertEquals("abaco", MinMax.min(List.of("abaco", "barco", "zazza"), (o1, o2) -> o1.compareTo(o2)));
    }
}