package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountYZTest {
    @Test
    void countYZ() {
        assertEquals(2, CountYZ.countYZ("fez day"));
        assertEquals(2, CountYZ.countYZ("day fez"));
        assertEquals(2, CountYZ.countYZ("day fyyyz"));
        assertEquals(2, CountYZ.countYZ("daY fyyYZ"));
        assertEquals(0, CountYZ.countYZ("hello world!"));
        assertEquals(1, CountYZ.countYZ("we love technology"));
    }
}
