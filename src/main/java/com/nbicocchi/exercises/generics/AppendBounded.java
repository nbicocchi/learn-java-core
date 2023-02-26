package com.nbicocchi.exercises.generics;

import java.util.List;

public class AppendBounded {
    public static <T> void append(List<T> a, List<? extends T> b) {
        a.addAll(b);
    }
}
