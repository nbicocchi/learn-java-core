package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FillArrayTest {

    @Test
    void fillArray() {
        assertArrayEquals(new double[]{1, 1, 1}, FillArray.fillArray(3, 1.0, false));
        assertArrayEquals(new double[]{5, 5, 5, 5}, FillArray.fillArray(4, 5.0, false));
        assertArrayEquals(new double[]{10, 10, 10, 10, 10, 10}, FillArray.fillArray(6, 10.0, true), 0.5);
        assertArrayEquals(new double[]{1, 1, 1, 1, 1, 1}, FillArray.fillArray(6, 1.0, true), 0.05);
    }
}