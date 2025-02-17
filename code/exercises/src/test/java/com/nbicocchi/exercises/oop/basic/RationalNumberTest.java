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
        RationalNumber r5 = new RationalNumber(1, 3);
        RationalNumber r6 = new RationalNumber(-7, 2);
        assertEquals(new RationalNumber(-19, 6), r5.add(r6));
        RationalNumber r7 = new RationalNumber(-13, 3);
        RationalNumber r8 = new RationalNumber(-6, 5);
        assertEquals(new RationalNumber(-83, 15), r7.add(r8));
    }

    @Test
    void multiply() {
        RationalNumber r1 = new RationalNumber(6, 3);
        RationalNumber r2 = new RationalNumber(6, 3);
        assertEquals(new RationalNumber(4, 1), r1.multiply(r2));
        RationalNumber r3 = new RationalNumber(5, 2);
        RationalNumber r4 = new RationalNumber(-1, 4);
        assertEquals(new RationalNumber(-5, 8), r3.multiply(r4));
        RationalNumber r5 = new RationalNumber(-7, 2);
        RationalNumber r6 = new RationalNumber(-2, 3);
        assertEquals(new RationalNumber(7, 3), r5.multiply(r6));
    }

    @Test
    void testEquals() {
        RationalNumber r1 = new RationalNumber(6, 3);
        RationalNumber r2 = new RationalNumber(6, 3);
        RationalNumber r3 = new RationalNumber(1, 10);
        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
        assertNotEquals(null, r1);
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