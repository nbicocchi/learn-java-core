package com.nbicocchi.exercises.oop.basic;

import java.util.Arrays;

/**
 * A resizable int array providing methods for its manipulation instead of the [] notation.
 */
public class EnhancedResizableArray {
    public static final int DEFAULT_CAPACITY = 4;
    int[] v;

    /**
     * Build an enhanced int array with a default capacity
     */
    public EnhancedResizableArray() {
        this.v = new int[DEFAULT_CAPACITY];
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
     * Sets the element stored at the specified array index with a new value.
     * If the underlying int[] capacity is smaller than index, a new int[] (with a capacity of index * 2 items) have to be allocated.
     * All the newly allocated elements have to be set to 0.
     * @param index the index to be set
     * @param value the value to be set
     */
    public void set(int index, int value) {
        if (index >= v.length) {
            int[] tmp = new int[index * 2];
            System.arraycopy(v, 0, tmp, 0, v.length);
            v = tmp;
        }
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
     * Fills the array with the specified value
     * @param value the specified value
     */
    public void fill(int value) {
        Arrays.fill(v, value);
    }

    /**
     * Returns a copy of the array of the values
     * @return the array
     */
    public int[] toArray() {
        return Arrays.copyOf(v, v.length);
    }

    /**
     * Returns the size of the array
     * @return the size of the array
     */
    public int length() {
        return v.length;
    }
}
