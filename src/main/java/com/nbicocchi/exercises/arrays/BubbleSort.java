package com.nbicocchi.exercises.arrays;

public class BubbleSort {
    public static int[] bubbleSort(int[] v) {
        int[] sorted = new int[v.length];
        System.arraycopy(v, 0, sorted, 0, v.length);
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < sorted.length - 1; i++) {
                if (sorted[i] > sorted[i + 1]) {
                    changed = true;
                    int tmp = sorted[i];
                    sorted[i] = sorted[i + 1];
                    sorted[i + 1] = tmp;
                }
            }
        }
        return sorted;
    }
}
