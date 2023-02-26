package com.nbicocchi.exercises.oop.basic;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * Write a class named Vector representing a vector of integers with additional features.
 * Supported methods:
 * void init() initialize the vector with random numbers [0, 100]
 * void sort() sorts the vector in ascending order
 * String show() returns a String showing the content of the vector
 * int search(int value) returns the index in which "value" is stored. -1 if "value" is missing.
 *
 * @author Nicola Bicocchi
 */
public class Vector {
    int[] v;

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
        StringBuilder b = new StringBuilder();
        b.append("[");
        for (int j : v) {
            b.append(String.format("%d, ", j));
        }
        b.append("]");
        return b.toString();
    }

    public int search(int value) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public void sort() {
        boolean changed;
        for (int i = 0; i < v.length - 1; i++) {
            changed = false;
            for (int j = 0; j < v.length - i - 1; j++) {
                if (v[j] > v[j + 1]) {
                    changed = true;
                    int tmp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = tmp;
                }
            }
            if (!changed)
                break;
        }
    }

    public void init() {
        RandomGenerator rnd = RandomGeneratorFactory.of("Random").create();
        for (int i = 0; i < v.length; i++) {
            v[i] = rnd.nextInt(100);
        }
    }
}
