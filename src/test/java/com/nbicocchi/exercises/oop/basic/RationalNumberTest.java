package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RationalNumberTest {
    @Test
    public void plus() {
        RationalNumber r1 = new RationalNumber(1, 2);
        RationalNumber r2 = new RationalNumber(1, 2);
        assertEquals(4.0, r1.add(r2).getNumerator(), 0);
        assertEquals(4.0, r1.add(r2).getDenominator(), 0);
    }

    @Test
    public void times() {
        RationalNumber r1 = new RationalNumber(1, 2);
        RationalNumber r2 = new RationalNumber(1, 2);
        assertEquals(1.0, r1.multiply(r2).getNumerator(), 0);
        assertEquals(4.0, r1.multiply(r2).getDenominator(), 0);
    }
}