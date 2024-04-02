package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnionTest {

    @Test
    void union() {
        assertEquals(Set.of(1,2,3,4,5,6), Union.union(Set.of(1, 2, 3), Set.of(4, 5, 6)));
        assertEquals(Set.of(1,2,3,4,5), Union.union(Set.of(1, 2, 3), Set.of(3, 4, 5)));
        assertEquals(Set.of(), Union.union(Set.of(), Set.of()));
    }

    @Test
    void unionAddAll() {
        assertEquals(Set.of(1,2,3,4,5,6), Union.unionAddAll(Set.of(1, 2, 3), Set.of(4, 5, 6)));
        assertEquals(Set.of(1,2,3,4,5), Union.unionAddAll(Set.of(1, 2, 3), Set.of(3, 4, 5)));
        assertEquals(Set.of(), Union.unionAddAll(Set.of(), Set.of()));
    }
}