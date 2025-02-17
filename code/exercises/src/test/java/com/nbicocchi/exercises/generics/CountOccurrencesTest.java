package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountOccurrencesTest {

    @Test
    void countOccurrencesPlain() {
        assertEquals(1, CountOccurrences.countOccurrencesPlain(new Integer[]{1, 2, 3}, 3));
        assertEquals(3, CountOccurrences.countOccurrencesPlain(new Integer[]{1, 2, 3, 3, 3}, 3));
        assertEquals(2, CountOccurrences.countOccurrencesPlain(new Integer[]{null, 2, null}, null));
        assertEquals(1, CountOccurrences.countOccurrencesPlain(new Integer[]{null, 2, null}, 2));
    }

    @Test
    void countOccurrencesObjects() {
        assertEquals(1, CountOccurrences.countOccurrencesObjects(new Integer[]{1, 2, 3}, 3));
        assertEquals(3, CountOccurrences.countOccurrencesObjects(new Integer[]{1, 2, 3, 3, 3}, 3));
        assertEquals(2, CountOccurrences.countOccurrencesObjects(new Integer[]{null, 2, null}, null));
        assertEquals(1, CountOccurrences.countOccurrencesObjects(new Integer[]{null, 2, null}, 2));
    }
}