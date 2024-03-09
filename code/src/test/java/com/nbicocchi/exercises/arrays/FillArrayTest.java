package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FillArrayTest {

    @Test
    void fillArray() {
        assertArrayEquals(new double[]{1}, FillArray.fillArray(1, 1.0, false));
        assertArrayEquals(new double[]{5, 5, 5, 5, 5}, FillArray.fillArray(5, 5.0, false));
        assertArrayEquals(new double[]{10, 10, 10, 10, 10}, FillArray.fillArray(5, 10.0, true), 0.5);
        assertArrayEquals(new double[]{1, 1, 1, 1, 1}, FillArray.fillArray(5, 1.0, true), 0.05);
    }
}