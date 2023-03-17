package com.nbicocchi.exercises.oop.basic;

import java.util.Arrays;
import java.util.random.RandomGenerator;

/**
 * An enhanced int array providing methods for its manipulation instead of the [] notation.
 */
public class EnhancedArray {
    int[] v;

    /**
     * Build an enhanced int array with the specified capacity
     * @param capacity the capacity of the enhanced array
     */
    public EnhancedArray(int capacity) {
        this.v = new int[capacity];
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
     * Resets all the elements of the array to zero
     */
    public void resetZero() {
        Arrays.fill(v, 0);
    }

    /**
     * Resets all the elements to random numbers comprised between [0, size()]
     */
    public void resetRandom() {
        RandomGenerator rnd = RandomGenerator.getDefault();
        for (int i = 0; i < v.length; i++) {
            v[i] = rnd.nextInt(v.length);
        }
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
