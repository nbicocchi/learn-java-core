package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEveryWhereTest {
    @Test
    void isEveryWhere() {
        assertTrue(IsEveryWhere.isEveryWhere(new int[]{1, 2, 1, 3}, 1));
        assertFalse(IsEveryWhere.isEveryWhere(new int[]{1, 2, 1, 3}, 2));
        assertFalse(IsEveryWhere.isEveryWhere(new int[]{1, 2, 1, 3, 4}, 1));
    }
}