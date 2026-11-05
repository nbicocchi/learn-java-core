package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MoveZerosEndTest {
    @Test
    public void moveZerosEnd() {
        assertArrayEquals(new int[]{4, 2, 0, 0}, MoveZerosEnd.moveZerosEnd(new int[]{0, 0, 4, 2}));
        assertArrayEquals(new int[]{9, 4, 0, 0}, MoveZerosEnd.moveZerosEnd(new int[]{9, 0, 0, 4}));
        assertArrayEquals(new int[]{1, 0, 0, 0}, MoveZerosEnd.moveZerosEnd(new int[]{1, 0, 0, 0}));
    }
}
