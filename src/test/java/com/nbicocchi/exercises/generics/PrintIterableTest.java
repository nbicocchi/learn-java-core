package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintIterableTest {
    @Test
    void printIterable() {
        assertEquals("5, 4, 3, ", PrintIterable.printIterable(List.of(5, 4, 3)));
        assertEquals("1, 2, 3, ", PrintIterable.printIterable(new LinkedList<>(List.of(1, 2, 3))));
    }
}