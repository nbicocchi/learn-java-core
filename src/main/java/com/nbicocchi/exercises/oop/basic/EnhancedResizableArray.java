package com.nbicocchi.exercises.oop.basic;

import java.util.Arrays;

/**
 * An enhanced int array providing methods for its manipulation instead of the [] notation.
 */
public class EnhancedResizableArray {
    public static final int DEFAULT_CAPACITY = 4;
    int size;
    int[] v;

    /**
     * Build an enhanced int array with a default capacity
     */
    public EnhancedResizableArray() {
        this.v = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Adds a value to the resizable array.
     * @param value the value to be added
     */
    public void add(int value) {
        if (size >= v.length) {
            int[] tmp = new int[v.length * 2];
            System.arraycopy(v, 0, tmp, 0, v.length);
            v = tmp;
        }
        set(size++, value);
    }

    /**
     * Removes the value at the specified index
     * @param index the index of the value to be removed
     */
    public void remove(int index) {
        System.arraycopy(v, index + 1, v, index, v.length - index - 1);
        size--;
    }

    /**
     * Returns the element stored at the specified array index
     * @param index the index to be retrieved
     * @return the element stored at the specified index
     */
    public int get(int index) {
        return v[index];
    }

    /**
     * Sets the element stored at the specified array index with a new value
     * @param index the index to be set
     * @param value the value to be set
     */
    public void set(int index, int value) {
        v[index] = value;
    }

    /**
     * Verifies if the specified int value is contained within the array
     * @param value the value to be verified
     * @return true if the value is contained within the array
     */
    public boolean contains(int value) {
        for (int i : v) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a copy of the array of the values
     * @return the array
     */
    public int[] toArray() {
        return Arrays.copyOf(v, size);
    }

    /**
     * Returns the size of the array
     * @return the size of the array
     */
    public int size() {
        return size;
    }
}
