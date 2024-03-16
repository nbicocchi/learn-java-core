package com.nbicocchi.exercises.oop.basic;

import java.util.Objects;

/**
 * A rational number supporting addition and multiplication with other rational numbers.
 */
public class RationalNumber {
    int numerator, denominator;

    /**
     * Constructs and initializes a new rational number with the specified numerator and denominator different form 0
     * @param numerator the numerator ot the rational
     * @param denominator the denominator ot the rational
     */
    public RationalNumber(int  numerator, int denominator) {
        if(denominator != 0) {
            int commonDivisor = greatestCommonDivisor(numerator,denominator);
            setNumerator(numerator/commonDivisor);
            setDenominator(denominator/commonDivisor);
        }
        else {
            System.out.println("You cannot have a rational number with denominator 0");
        }

    }
    /**
     * Returns the numerator of the rational
     * @return the numerator
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Initialize the numerator of the rational number
     * @param numerator
     */
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }
    /**
     * Returns the denominator of the rational
     * @return the denominator
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * check if the denominator is different from 0 then initialize the denominator of the rational number
     * @param denominator
     */
    public void setDenominator(int denominator) {
            this.denominator = denominator;
    }
    /**
     * Adds one rational to the current rational
     * @param rationalNumber the rational to be added
     * @return a new rational representing the sum
     */
    public RationalNumber add(RationalNumber rationalNumber) {
        int resDenominator = leastCommonMultiple(this.denominator, rationalNumber.getDenominator());
        int resNumerator = (resDenominator/this.denominator*this.numerator) + (resDenominator/rationalNumber.denominator*rationalNumber.numerator);
        return (new RationalNumber(resNumerator, resDenominator) );
    }
    /**
     * Multiples one rational to the current rational
     * @param rationalNumber the rational to be multiplied
     * @return a new rational representing the product
     */
    public RationalNumber multiply(RationalNumber rationalNumber) {
        return new RationalNumber(this.numerator*rationalNumber.getNumerator(), this.denominator*rationalNumber.getDenominator());
    }

    /**
     * Return the greatest common divisor of two integer numbers
     * @param a the first integer number
     * @param b the second integer number
     * @return the greatest common divisor
     */
    public static int greatestCommonDivisor(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        int rest = max % min;
        if (max % min == 0) {
            return min;
        }
        else {
            return greatestCommonDivisor(min, rest);
        }
    }

    /**
     * Return the least common multiple of two integer numbers
     * @param a the first integer number
     * @param b the second integer number
     * @return the least common multiple
     */
    public static int leastCommonMultiple(int a, int b) {
        return Math.abs(a * b) / greatestCommonDivisor(a, b);
    }

    @Override
    public String toString() {
        return "RationalNumber{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RationalNumber that)) {
            return false;
        }
        return getNumerator() == that.getNumerator() && getDenominator() == that.getDenominator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumerator(), getDenominator());
    }

}