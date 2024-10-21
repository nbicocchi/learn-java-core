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

        int[][] matrix1 = {{4, 5, 3}, {2, 1, 6}, {7, 0}, {1, 5, 2, 7}};
        int[][] deepCopy1 = DeepCopy.deepCopy(matrix1);
        assertTrue(deepCopy1[0] != matrix1[0]);
        assertTrue(deepCopy1[1] != matrix1[1]);
        assertTrue(deepCopy1[2] != matrix1[2]);
        assertTrue(deepCopy1[3] != matrix1[3]);

    }
}