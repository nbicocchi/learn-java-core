package com.nbicocchi.exercises.oop.polynomials;

/**
 * Interface representing a polynomial with arbitrary grade
 *
 * @author Nicola Bicocchi
 */
public interface Poly {
    /**
     * Returns the degree of the polynomial
     *
     * @return the degree of the polynomial
     */
    int degree();

    /**
     * Returns a new polynomial which is the derivative of the current object
     * More specifically, the call invoked on object (1), returns object (2) (1)
     * c0 + c_1 * x + c_2 * x^2 + ... + c_n * x^n (2) c1 + 2c_2 * x + ... + nc_n
     * * x^(n-1)
     *
     * @return Returns a new polynomial which is the derivative of the current
     * object
     */
    Poly derivative();

    /**
     * Returns the coefficient of the monomial with the specified degree
     *
     * @param degree The degree to be queried (get the coefficient)
     *
     * @return The coefficient of the monomial with the specified degree
     */
    double coefficient(int degree);

    /**
     * Returns a double[] containing all the coefficients
     *
     * @return A double[] containing all the coefficients
     */
    double[] coefficients();
}

