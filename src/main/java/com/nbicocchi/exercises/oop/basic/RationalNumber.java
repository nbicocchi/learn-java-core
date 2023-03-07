package com.nbicocchi.exercises.oop.basic;

import java.util.Objects;

/**
 * A rational number supporting addition and multiplication with other rational numbers.
 */
public class RationalNumber {
    final int numerator;
    final int denominator;

    /**
     * Constructs and initializes a new rational number with the specified numerator and denominator
     * @param numerator the numerator ot the rational
     * @param denominator the denominator ot the rational
     */
    public RationalNumber(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Returns the numerator of the rational
     * @return the numerator
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Returns the denominator of the rational
     * @return the denominator
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Adds one rational to the current rational
     * @param o the rational to be added
     * @return a new rational representing the sum
     */
    public RationalNumber add(RationalNumber o) {
        int n = numerator * o.denominator + o.numerator * denominator;
        int d = denominator * o.denominator;
        return new RationalNumber(n, d);
    }

    /**
     * Multiples one rational to the current rational
     * @param o the rational to be multiplied
     * @return a new rational representing the product
     */
    public RationalNumber multiply(RationalNumber o) {
        int n = this.numerator * o.numerator;
        int d = this.denominator * o.denominator;
        return new RationalNumber(n, d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RationalNumber that = (RationalNumber) o;
        return numerator == that.numerator && denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return "Rational{" + "numerator=" + numerator + ", denominator=" + denominator + '}';
    }
}
