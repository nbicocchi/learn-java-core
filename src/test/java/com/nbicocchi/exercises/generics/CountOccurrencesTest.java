package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountOccurrencesTest {

    @Test
    void countOccurrences() {
        assertEquals(1, CountOccurrences.countOccurrences(new Integer[]{1, 2, 3}, 3));
        assertEquals(3, CountOccurrences.countOccurrences(new Integer[]{1, 2, 3, 3, 3}, 3));
        assertEquals(2, CountOccurrences.countOccurrences(new Integer[]{null, 2, null}, null));
        assertEquals(1, CountOccurrences.countOccurrences(new Integer[]{null, 2, null}, 2));
    }
}