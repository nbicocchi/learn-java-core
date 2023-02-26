package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinMaxTest {

    @Test
    void max() {
        Comparator<Integer> comparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        assertEquals(5, MinMax.max(List.of(1, 2, 3, 4, 5), comparator));
    }

    @Test
    void min() {
        Comparator<Integer> comparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        assertEquals(1, MinMax.min(List.of(1, 2, 3, 4, 5), comparator));
    }
}