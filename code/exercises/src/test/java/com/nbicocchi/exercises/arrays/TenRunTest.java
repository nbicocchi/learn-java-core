package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TenRunTest {
    @Test
    void tenRun() {
        assertArrayEquals(new int[]{2, 10, 10, 10, 20, 20}, TenRun.tenRun(new int[]{2, 10, 3, 4, 20, 5}));
        assertArrayEquals(new int[]{10, 10, 20, 20}, TenRun.tenRun(new int[]{10, 1, 20, 2}));
        assertArrayEquals(new int[]{10, 10, 10, 20}, TenRun.tenRun(new int[]{10, 1, 9, 20}));
    }
}