package com.nbicocchi.exercises.e6_generics;

import java.util.List;

public class Append {
    public static <T> void append(List<T> dst, List<T> src) {
        dst.addAll(src);
    }
}
