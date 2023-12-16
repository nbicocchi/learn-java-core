package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchArrayTest {
    final String[] words = {"car", "boat", "bike", "plane"};

    @Test
    void searchBruteForce() {
        assertTrue(SearchArray.containsBruteForce(words, "bike"));
        assertFalse(SearchArray.containsBruteForce(words, "helicopter"));
    }

    @Test
    void searchBinary() {
        assertTrue(SearchArray.containsBinary(words, "bike"));
        assertFalse(SearchArray.containsBinary(words, "helicopter"));
    }
}