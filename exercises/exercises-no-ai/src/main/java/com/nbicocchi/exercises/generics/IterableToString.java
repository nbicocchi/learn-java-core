package com.nbicocchi.exercises.generics;

public class IterableToString {
    public static <E> String iterableToString(Iterable<E> src) {
        StringBuilder builder = new StringBuilder();
        for (E e : src) {
            builder.append(e).append(", ");
        }
        return builder.toString();
    }
}
