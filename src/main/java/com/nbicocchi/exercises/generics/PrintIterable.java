package com.nbicocchi.exercises.generics;

import java.util.Iterator;

public class PrintIterable {
    public static <E> String printIterable(Iterable<E> src) {
        StringBuilder builder = new StringBuilder();
        for (E e : src) {
            builder.append(e).append(", ");
        }
        return builder.toString();
    }
}
