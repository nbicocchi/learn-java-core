package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppendTest {

    @Test
    void append() {
        List<Integer> a = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> b = new ArrayList<>(List.of(4, 5));
        Append.append(a, b);
        assertEquals(List.of(1, 2, 3, 4, 5), a);
    }
}