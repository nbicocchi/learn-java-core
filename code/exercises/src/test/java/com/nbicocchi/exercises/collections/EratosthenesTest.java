package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EratosthenesTest {

    @Test
    void eratosthenes() {
        assertEquals(Set.of(), Eratosthenes.eratosthenes(1));
        assertEquals(Set.of(2, 3), Eratosthenes.eratosthenes(3));
        assertEquals(Set.of(2, 3, 5, 7, 11, 13, 17, 19, 23), Eratosthenes.eratosthenes(26));
    }
}