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
        int gcm = greatestCommonDivisor(numerator, denominator);
        this.numerator = numerator / gcm;
        this.denominator = denominator / gcm;
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
        int lcm = leastCommonMultiple(denominator, o.getDenominator());
        int num = numerator * (lcm / denominator) + o.getNumerator() * (lcm / o.getDenominator());
        return new RationalNumber(num, lcm);
    }

    /**
     * Multiples one rational to the current rational
     * @param o the rational to be multiplied
     * @return a new rational representing the product
     */
    public RationalNumber multiply(RationalNumber o) {
        int num = numerator * o.getNumerator();
        int den = denominator * o.getDenominator();
        return new RationalNumber(num, den);
    }

    /**
     * Return the greatest common divisor of two integer numbers
     * @param a the first integer number
     * @param b the second integer number
     * @return the greatest common divisor
     */
    static int greatestCommonDivisor(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);

        int rest = max % min;
        if (max % min == 0) {
            return min;
        } else {
            return greatestCommonDivisor(min, rest);
        }
    }

    /**
     * Return the least common multiple of two integer numbers
     * @param a the first integer number
     * @param b the second integer number
     * @return the least common multiple
     */
    static int leastCommonMultiple(int a, int b) {
        return Math.abs(a * b) / greatestCommonDivisor(a, b);
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
