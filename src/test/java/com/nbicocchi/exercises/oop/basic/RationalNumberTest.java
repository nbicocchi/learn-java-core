package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalNumberTest {

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
}