package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalNumberTest {

    @Test
    void add() {
        RationalNumber r1 = new RationalNumber(2, 3);
        RationalNumber r2 = new RationalNumber(2, 3);
        assertEquals(new RationalNumber(12, 9), r1.add(r2));
    }

    @Test
    void multiply() {
        RationalNumber r1 = new RationalNumber(2, 3);
        RationalNumber r2 = new RationalNumber(2, 3);
        assertEquals(new RationalNumber(4, 9), r1.multiply(r2));
    }
}