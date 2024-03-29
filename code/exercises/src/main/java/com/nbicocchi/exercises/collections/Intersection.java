package com.nbicocchi.exercises.collections;

import java.util.HashSet;
import java.util.Set;

public class Intersection {
    public static Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> intersection = new HashSet<>();
        for (int item : first) {
            if (second.contains(item)) {
                intersection.add(item);
            }
        }
        return intersection;
    }

    public static Set<Integer> intersectionRetainAll(Set<Integer> first, Set<Integer> second) {
        Set<Integer> intersection = new HashSet<>(first);
        intersection.retainAll(second);
        return intersection;
    }
}
