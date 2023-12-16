package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeepCopyTest {

    @Test
    void deepCopy() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        int[][] deepCopy = DeepCopy.deepCopy(matrix);
        assertTrue(deepCopy[0] != matrix[0]);
        assertTrue(deepCopy[1] != matrix[1]);
    }
}