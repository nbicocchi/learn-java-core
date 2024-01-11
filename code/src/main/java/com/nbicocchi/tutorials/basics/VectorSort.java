package com.nbicocchi.tutorials.basics;

import java.util.random.RandomGenerator;

public class VectorSort {
    int[] data;

    public VectorSort(int length) {
        this.data = new int[length];
        init();
    }

    public void init() {
        RandomGenerator rnd = RandomGenerator.getDefault();
        for (int i = 0; i < data.length; i++) {
            data[i] = rnd.nextInt(100);
        }
    }

    public void show() {
        for (int i = 0; i < data.length; i++) {
            System.out.printf("[i:%d] -> [value:%d]\n", i, data[i]);
        }
        System.out.printf("\n");
    }

    public void sort() {
        boolean changed;
        for (int i = 0; i < data.length - 1; i++) {
            changed = false;
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j+1]) {
                    changed = true;
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
            if (!changed) break;
        }
    }

    public static void main(String[] args) {
        VectorSort v = new VectorSort(10);
        v.show();
        v.sort();
        v.show();
    }
}
