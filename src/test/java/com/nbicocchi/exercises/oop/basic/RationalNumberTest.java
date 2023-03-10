package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalNumberTest {

    @Test
    void greatestCommonDivisor() {

        assertEquals(7, RationalNumber.greatestCommonDivisor(7, 14));

    }

    @Test
    void leastCommonMultiple() {
        assertEquals(21, RationalNumber.leastCommonMultiple(3, 7));

    }

    @Test
    void add() {
        RationalNumber rationalNumber1 = new RationalNumber(1, 2);
        RationalNumber rationalNumber2 = new RationalNumber(2, 3);

        RationalNumber rationalNumberAdded = rationalNumber1.add(rationalNumber2);

        assertEquals(7, rationalNumberAdded.getNumerator());
        assertEquals(6, rationalNumberAdded.getDenominator());
    }

    @Test
    void multiply() {
        RationalNumber rationalNumber1 = new RationalNumber(1, 5);
        RationalNumber rationalNumber2 = new RationalNumber(8, 1);

        RationalNumber rationalNumberAdded = rationalNumber1.multiply(rationalNumber2);

        assertEquals(8, rationalNumberAdded.getNumerator());
        assertEquals(5, rationalNumberAdded.getDenominator());
    }

    @Test
    void testToString() {
        RationalNumber rationalNumber = new RationalNumber(5, 7);

        System.out.println(rationalNumber.toString());
    }

    @Test
    void testEquals() {
        RationalNumber rationalNumber1 = new RationalNumber(1, 5);
        RationalNumber rationalNumber2 = new RationalNumber(1, 5);

        assertEquals(rationalNumber1, rationalNumber2);
    }
}