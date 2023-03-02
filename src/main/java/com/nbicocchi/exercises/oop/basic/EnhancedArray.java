package com.nbicocchi.exercises.oop.basic;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class EnhancedArray {
    private int[] v;

    public EnhancedArray(int capacity) {
        this.v = new int[capacity];
    }

    public int size() {
        return v.length;
    }

    public int get(int index) {
        return v[index];
    }

    public void set(int index, int value) {
        v[index] = value;
    }

    public int indexOf(int value) {
        int[] copy = Arrays.copyOf(v, v.length);
        Arrays.sort(copy);
        return Arrays.binarySearch(copy, value);
    }

    public void resetZero() {
        for (int i = 0; i < v.length; i++) {
            v[i] = 0;
        }
    }

    public void resetRandom() {
        RandomGenerator rnd = RandomGenerator.getDefault();
        for (int i = 0; i < v.length; i++) {
            v[i] = rnd.nextInt(v.length);
        }
    }

    public int[] toArray() {
        return Arrays.copyOf(v, v.length);
    }
}
