package com.nbicocchi.exercises.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivisionSquaredTest {
    @Test
    void divisionSquaredTest() {
        assertEquals(4, DivisionSquared.divisionSquared(4, 2));
        assertEquals(4, DivisionSquared.divisionSquared(-8, 4));
        assertEquals(0, DivisionSquared.divisionSquared(4, 0));
        assertEquals(0, DivisionSquared.divisionSquared(-8, 0));
    }
}
