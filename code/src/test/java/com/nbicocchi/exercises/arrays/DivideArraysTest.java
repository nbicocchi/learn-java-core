package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DivideArraysTest {
    private static final double DELTA = 1e-09;

    @Test
    public void divideArrays() {
        assertArrayEquals(new double[]{1 / 3.0, 1 / 3.0, 1 / 3.0}, DivideArrays.divideArrays(new double[]{1, 1, 1}, new double[]{3, 3, 3}), DELTA);
        assertArrayEquals(new double[]{0.2, 0.1, 0.1}, DivideArrays.divideArrays(new double[]{2, 1, 1}, new double[]{10, 10, 10}), DELTA);
        assertArrayEquals(new double[]{2.0, 2.0}, DivideArrays.divideArrays(new double[]{10, 10}, new double[]{5, 5}), DELTA);
    }
}
