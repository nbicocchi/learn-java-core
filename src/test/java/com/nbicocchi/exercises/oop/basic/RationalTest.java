package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RationalTest {
    @Test
    public void plus() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 2);
        assertEquals(4.0, r1.plus(r2).getNumerator(), 0);
        assertEquals(4.0, r1.plus(r2).getDenominator(), 0);
    }

    @Test
    public void times() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 2);
        assertEquals(1.0, r1.times(r2).getNumerator(), 0);
        assertEquals(4.0, r1.times(r2).getDenominator(), 0);
    }
}