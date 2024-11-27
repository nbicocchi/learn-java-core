package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseTest {

    @Test
    void reverse() {
        List<Integer> l1 = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        Reverse.reverse(l1);
        assertEquals(List.of(5, 4, 3, 2, 1), l1);
        List<Integer> l2 = new ArrayList<>(List.of(1, 2));
        Reverse.reverse(l2);
        assertEquals(List.of(2, 1), l2);
        List<Integer> l3 = new ArrayList<>(List.of());
        Reverse.reverse(l3);
        assertEquals(List.of(), l3);
        List<String> l4 = new ArrayList<>(List.of("hello", "world"));
        Reverse.reverse(l4);
        assertEquals(List.of("world", "hello"), l4);
    }
}