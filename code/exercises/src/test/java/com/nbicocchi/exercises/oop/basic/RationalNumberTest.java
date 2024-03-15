package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalNumberTest {

    @Test
    void getNumerator() {
        RationalNumber rn = new RationalNumber(9, 3);
        assertEquals(3, rn.getNumerator());
    }
    @Test
    void getDenominator() {
        RationalNumber rn = new RationalNumber(9, 3);
        assertEquals(1, rn.getDenominator());
    }

    @Test
    void add() {
        RationalNumber r1 = new RationalNumber(2, 3);
        RationalNumber r2 = new RationalNumber(2, 3);
        assertEquals(new RationalNumber(4, 3), r1.add(r2));
        RationalNumber r3 = new RationalNumber(5, 10);
        RationalNumber r4 = new RationalNumber(5, 10);
        assertEquals(new RationalNumber(1, 1), r3.add(r4));
    }

    @Test
    void multiply() {
        RationalNumber r1 = new RationalNumber(6, 3);
        RationalNumber r2 = new RationalNumber(6, 3);
        assertEquals(new RationalNumber(4, 1), r1.multiply(r2));
    }

    @Test
    void testEquals() {
        RationalNumber r1 = new RationalNumber(6, 3);
        RationalNumber r2 = new RationalNumber(6, 3);
        RationalNumber r3 = new RationalNumber(1, 10);

        assertTrue(r1.equals(r2));
        assertFalse(r1.equals(r3));
    }

    @Test
    void testToString() {
        RationalNumber r1 = new RationalNumber(1, 3);
        assertEquals("RationalNumber{numerator=1, denominator=3}", r1.toString());
    }

    @Test
    void testHashCode() {
        RationalNumber r1 = new RationalNumber(3, 4);
        RationalNumber r2 = new RationalNumber(3, 4);

        assertEquals(r2.hashCode(), r1.hashCode());
    }
}