package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BubbleSortTest {
    @Test
    public void bubbleSort() {
        int[] v1 = {6, 3, 4, 2};
        BubbleSort.bubbleSort(v1);
        assertArrayEquals(new int[]{2, 3, 4, 6}, v1);

        int[] v2 = {1, 0, 0, 0};
        BubbleSort.bubbleSort(v2);
        assertArrayEquals(new int[]{0, 0, 0, 1}, v2);

        assertArrayEquals(new int[]{2, 3, 4, 6}, BubbleSort.bubbleSortCopy(new int[]{6, 3, 4, 2}));
        assertArrayEquals(new int[]{0, 0, 0, 1}, BubbleSort.bubbleSortCopy(new int[]{1, 0, 0, 0}));
    }
}
