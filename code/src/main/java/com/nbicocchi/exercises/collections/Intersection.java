package com.nbicocchi.exercises.collections;

import java.util.HashSet;
import java.util.Set;

public class Intersection {
    public static Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> tmp = new HashSet<>();
        for (int i : first) {
            if (second.contains(i)) {
                tmp.add(i);
            }
        }
        return tmp;
    }
}
