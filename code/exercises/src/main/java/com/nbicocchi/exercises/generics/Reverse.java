package com.nbicocchi.exercises.generics;

import java.util.List;

public class Reverse {
    public static <T> void reverse(List<T> list) {
        int size = list.size();
        for (int i = 0; i < Math.floor((double) size / 2); i++) {
            T tmp = list.get(i);
            list.set(i, list.get(size - i - 1));
            list.set(size - i - 1, tmp);
        }
    }
}
