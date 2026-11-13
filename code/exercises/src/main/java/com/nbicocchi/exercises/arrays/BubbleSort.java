package com.nbicocchi.exercises.arrays;

import java.util.Arrays;

public class BubbleSort {
    public static int[] bubbleSortCopy(int[] v) {
        int[] copy = Arrays.copyOf(v, v.length);
        bubbleSort(copy);
        return copy;
    }

    public static void bubbleSort(int[] v) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < v.length - 1; i++) {
                if (v[i] > v[i + 1]) {
                    changed = true;
                    int tmp = v[i];
                    v[i] = v[i + 1];
                    v[i + 1] = tmp;
                }
            }
        }
    }
}
