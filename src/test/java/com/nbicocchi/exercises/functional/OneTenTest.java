package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneTenTest {
    @Test
    void oneTen() {
        assertEquals(List.of(20, 30, 40), OneTen.oneTen(List.of(1, 2, 3)));
        assertEquals(List.of(70, 90, 70, 90, 20), OneTen.oneTen(List.of(6, 8, 6, 8, 1)));
        assertEquals(List.of(110), OneTen.oneTen(List.of(10)));
    }
}