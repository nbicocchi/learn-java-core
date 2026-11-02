package com.nbicocchi.tutorials.oop.introduction;

import java.util.Arrays;
import java.util.Random;

/** Teaching class, unrelated to java.util.Vector. */
public final class Vector {
    private final int[] values;

    public Vector(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be non-negative");
        }
        values = new int[capacity];
        init();
    }

    private void init() {
        Random random = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(100);
        }
    }

    public void sort() {
        Arrays.sort(values);
    }

    /** Returns the first matching index, or -1, without modifying the array. */
    public int search(int value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
