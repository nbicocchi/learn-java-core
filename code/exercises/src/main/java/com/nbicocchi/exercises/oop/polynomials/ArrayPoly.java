package com.nbicocchi.exercises.oop.polynomials;

import java.util.Arrays;

/**
 * Polynomial with coefficients stored as a double[]
 *
 * @author Nicola Bicocchi
 */
public class ArrayPoly extends AbstractPoly {
    final double[] coefficients;

    public ArrayPoly(double[] coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public int degree() {
        return coefficients.length - 1;
    }

    @Override
    public Poly derivative() {
        return new ArrayPoly(derive());
    }

    @Override
    public double coefficient(int degree) {
        return coefficients[degree];
    }

    @Override
    public double[] coefficients() {
        return Arrays.copyOf(coefficients, coefficients.length);
    }
}
