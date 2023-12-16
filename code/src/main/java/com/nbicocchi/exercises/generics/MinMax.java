package com.nbicocchi.exercises.generics;

import java.util.Comparator;
import java.util.List;

public class MinMax {
    public static <T> T max(List<T> list, Comparator<? super T> cmp) {
        T candidate = list.get(0);
        for (T next : list) {
            if (cmp.compare(candidate, next) < 0) {
                candidate = next;
            }
        }
        return candidate;
    }

    public static <T> T min(List<T> list, Comparator<? super T> cmp) {
        T candidate = list.get(0);
        for (T next : list) {
            if (cmp.compare(candidate, next) > 0) {
                candidate = next;
            }
        }
        return candidate;
    }
}
