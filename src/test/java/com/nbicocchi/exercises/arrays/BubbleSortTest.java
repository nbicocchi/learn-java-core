package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BubbleSortTest {
    @Test
    public void bubbleSort() {
        assertArrayEquals(new int[]{2, 3, 4, 6}, BubbleSort.bubbleSort(new int[]{6, 3, 4, 2}));
        assertArrayEquals(new int[]{4, 7, 8, 9}, BubbleSort.bubbleSort(new int[]{9, 8, 7, 4}));
        assertArrayEquals(new int[]{0, 0, 0, 1}, BubbleSort.bubbleSort(new int[]{1, 0, 0, 0}));
    }
}
