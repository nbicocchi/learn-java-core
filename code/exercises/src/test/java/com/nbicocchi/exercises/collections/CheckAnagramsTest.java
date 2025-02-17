package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckAnagramsTest {

    @Test
    void areAnagrams() {
        assertTrue(CheckAnagrams.areAnagrams("dusty", "study"));
        assertTrue(CheckAnagrams.areAnagrams("elbow", "below"));
        assertTrue(CheckAnagrams.areAnagrams("night", "thing"));
        assertTrue(CheckAnagrams.areAnagrams("peach", "cheap"));
        assertFalse(CheckAnagrams.areAnagrams("peach", "chekp"));
        assertFalse(CheckAnagrams.areAnagrams("peach", "cheep"));
        assertFalse(CheckAnagrams.areAnagrams("peach", " cheap"));
        assertFalse(CheckAnagrams.areAnagrams("peach", "cheap "));
        assertFalse(CheckAnagrams.areAnagrams("", "cheap"));
        assertFalse(CheckAnagrams.areAnagrams("peach", ""));
        assertTrue(CheckAnagrams.areAnagrams("CAT", "act"));
        assertTrue(CheckAnagrams.areAnagrams("Night", "Thing"));
        assertFalse(CheckAnagrams.areAnagrams("Peach", "chekP"));
        assertFalse(CheckAnagrams.areAnagrams("VidEo Game", "give a dEMo"));
        assertTrue(CheckAnagrams.areAnagrams("DUSTY", "STUDY"));
        assertTrue(CheckAnagrams.areAnagrams("", ""));
    }
}