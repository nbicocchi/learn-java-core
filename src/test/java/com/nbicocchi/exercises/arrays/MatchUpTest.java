package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchUpTest {
    @Test
    void matchUp() {
        assertEquals(2, MatchUp.matchUp(new int[]{1, 2, 3}, new int[]{2, 3, 10}));
        assertEquals(3, MatchUp.matchUp(new int[]{1, 2, 3}, new int[]{2, 3, 5}));
        assertEquals(2, MatchUp.matchUp(new int[]{1, 2, 3}, new int[]{2, 3, 3}));
    }
}