package com.nbicocchi.exercises.oop.basic;

import java.util.Objects;

public class RationalNumber {

    private int denominator;
    private int numerator;

    public RationalNumber(int numerator, int denominator) {
        this.denominator = denominator;
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public RationalNumber add(RationalNumber rationalNumberToAdd) {

        int newDenominator = RationalNumber.leastCommonMultiple(this.denominator, rationalNumberToAdd.denominator);

        int newNumerator = (newDenominator / this.denominator) * this.numerator;
        newNumerator += (newDenominator / rationalNumberToAdd.denominator) * rationalNumberToAdd.numerator;

        return new RationalNumber(newNumerator, newDenominator);

    }

    public RationalNumber multiply(RationalNumber rationalNumberToMultiply) {

        return new RationalNumber(this.numerator * rationalNumberToMultiply.numerator,
                this.denominator * rationalNumberToMultiply.denominator);

    }

    @Override
    public String toString() {
        return String.format("%d / %d", this.numerator, this.denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RationalNumber that))
            return false;
        return getDenominator() == that.getDenominator() && getNumerator() == that.getNumerator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDenominator(), getNumerator());
    }

    public static int greatestCommonDivisor(int a, int b) {

        int biggerNumber = Math.max(a, b);
        int smallestNumber = Math.min(a, b);

        int rest = biggerNumber % smallestNumber;

        if (rest == 0)
            return smallestNumber;
        else
            return RationalNumber.greatestCommonDivisor(smallestNumber, rest);

    }

    public static int leastCommonMultiple(int a, int b) {
        return Math.abs(a * b) / RationalNumber.greatestCommonDivisor(a, b);
    }
}
