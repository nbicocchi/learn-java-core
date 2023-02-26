package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseTest {

    @Test
    void reverse() {
        List<Integer> l = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        Reverse.reverse(l);
        assertEquals(List.of(5, 4, 3, 2, 1), l);
    }
}