package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntersectionTest {

    @Test
    void intersection() {
        assertEquals(Set.of(), Intersection.intersection(Set.of(1, 2, 3), Set.of(4, 5, 6)));
        assertEquals(Set.of(3), Intersection.intersection(Set.of(1, 2, 3), Set.of(3, 4, 5)));
    }
}