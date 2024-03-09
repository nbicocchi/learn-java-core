package com.nbicocchi.exercises.functional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToString {
    public static <T> String toString(Stream<T> stream, int n) {
        return stream.limit(n).map(Object::toString).collect(Collectors.joining(","));
    }
}
