package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeepCopyTest {

    @Test
    void deepCopy() {
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}};
        int[][] deepCopy1 = DeepCopy.deepCopy(matrix1);
        assertNotSame(deepCopy1[0], matrix1[0]);
        assertNotSame(deepCopy1[1], matrix1[1]);

        int[][] matrix2 = {{4, 5, 3}, {2, 1, 6}, {7, 0}, {1, 5, 2, 7}};
        int[][] deepCopy2 = DeepCopy.deepCopy(matrix2);
        assertNotSame(deepCopy2[0], matrix2[0]);
        assertNotSame(deepCopy2[1], matrix2[1]);
        assertNotSame(deepCopy2[2], matrix2[2]);
        assertNotSame(deepCopy2[3], matrix2[3]);

    }
}