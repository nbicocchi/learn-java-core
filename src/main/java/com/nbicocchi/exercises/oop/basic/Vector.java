package com.nbicocchi.exercises.oop.basic;

import java.util.Arrays;
import java.util.random.RandomGenerator;

/**
 * Write a class named Vector representing a vector of integers with additional features.
 * Supported methods:
 * void init() initialize the vector with random numbers [0, 100]
 * void sort() sorts the vector in ascending order
 * String show() returns a String showing the content of the vector
 * int search(int value) returns the index in which "value" is stored. A negative number if "value" is missing.
 *
 * @author Nicola Bicocchi
 */
public class Vector {
    int[] v;

    public Vector(int capacity) {
        this.v = new int[capacity];
        init();
    }

    public Vector(int[] v) {
        this.v = v;
    }

    public int[] getV() {
        return v;
    }

    public void setV(int[] v) {
        this.v = v;
    }

    public String show() {
        return Arrays.toString(v);
    }

    public int search(int value) {
        int[] copy = Arrays.copyOf(v, v.length);
        Arrays.sort(copy);
        return Arrays.binarySearch(copy, value);
    }

    public void sort() {
        Arrays.sort(v);
    }

    public void init() {
        RandomGenerator rnd = RandomGenerator.getDefault();
        for (int i = 0; i < v.length; i++) {
            v[i] = rnd.nextInt(100);
        }
    }
}
