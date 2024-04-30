package com.nbicocchi.exercises.collections;

import java.util.HashSet;
import java.util.Set;

public class Intersection {
    public static Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> intersection = new HashSet<>();

        if(first.isEmpty() || second.isEmpty()){
            return intersection;
        }

        for (int item : first) {
            if (second.contains(item)) {
                intersection.add(item);
            }
        }
        return intersection;
    }

    public static Set<Integer> intersectionRetainAll(Set<Integer> first, Set<Integer> second) {
        if(first.isEmpty() || second.isEmpty()){
            return new HashSet<>();
        }
        Set<Integer> intersection = new HashSet<>(first);
        intersection.retainAll(second);
        return intersection;
    }
}
