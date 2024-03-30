package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckAnagramsArrTest {

    @Test
    void areAnagrams() {
        assertTrue(CheckAnagramsArr.areAnagrams("dusty", "study"));
        assertTrue(CheckAnagramsArr.areAnagrams("elbow", "below"));
        assertTrue(CheckAnagramsArr.areAnagrams("night", "thing"));
        assertTrue(CheckAnagramsArr.areAnagrams("peach", "cheap"));
        assertFalse(CheckAnagramsArr.areAnagrams("peach", "chekp"));
        assertFalse(CheckAnagramsArr.areAnagrams("peach", "cheep"));
        assertFalse(CheckAnagramsArr.areAnagrams("peach", " cheap"));
        assertFalse(CheckAnagramsArr.areAnagrams("peach", "cheap "));
        assertFalse(CheckAnagramsArr.areAnagrams("", "cheap"));
        assertFalse(CheckAnagramsArr.areAnagrams("peach", ""));
    }

}