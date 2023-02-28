package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideTest {
    private static final double DELTA = 1e-09;

    @Test
    void divide() {
        assertEquals(2.0, Divide.divide(4, 2), DELTA);
        assertEquals(2.0, Divide.divide(4, 2.0), DELTA);
        assertEquals(2.0, Divide.divide(4.0, 2.0), DELTA);
        assertEquals(2.0, Divide.divide(4.0, 2), DELTA);
    }
}