package com.nbicocchi.exercises.generics;

import java.util.List;

public class Append {
    public static <T> void append(List<T> a, List<T> b) {
        a.addAll(b);
    }
}
