package com.nbicocchi.exercises.oop.basic;

import java.util.Arrays;

/**
 * An immutable enhanced int array providing methods for its manipulation
 */
public class EnhancedImmutableArray {
    final int[] v;

    /**
     * Builds an immutable int array with the specified elements
     * @param array the array containing the elements
     */
    public EnhancedImmutableArray(int[] array) {
        this.v = Arrays.copyOf(array, array.length);
    }

    /**
     * Returns the size of the array
     * @return the size of the array
     */
    public int size() {
        return v.length;
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
        return Arrays.copyOf(v, v.length);
    }
}
