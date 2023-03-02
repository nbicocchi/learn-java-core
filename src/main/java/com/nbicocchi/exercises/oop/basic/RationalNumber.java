package com.nbicocchi.exercises.oop.basic;

public class RationalNumber {
    private final int numerator;
    private final int denominator;

    public RationalNumber(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public RationalNumber add(RationalNumber o) {
        int n = numerator * o.denominator + o.numerator * denominator;
        int d = denominator * o.denominator;
        return new RationalNumber(n, d);
    }

    public RationalNumber multiply(RationalNumber o) {
        int n = this.numerator * o.numerator;
        int d = this.denominator * o.denominator;
        return new RationalNumber(n, d);
    }

    @Override
    public String toString() {
        return "Rational{" + "numerator=" + numerator + ", denominator=" + denominator + '}';
    }
}
