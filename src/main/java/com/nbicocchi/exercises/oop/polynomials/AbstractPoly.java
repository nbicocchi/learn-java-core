package com.nbicocchi.exercises.oop.polynomials;

import java.util.Objects;

/**
 * An abstract class providing an implementation for shared parts of ArrayPoly
 * and ListPoly
 *
 * @author Nicola Bicocchi
 */
public abstract class AbstractPoly implements Poly {
    protected double[] derive() {
        double[] tmp = new double[Math.max(1, degree())];
        for (int i = 1; i <= degree(); i++) {
            tmp[i - 1] = coefficient(i) * i;
        }
        return tmp;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coefficients());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Poly p)) {
            return false;
        }
        if (degree() != p.degree()) {
            return false;
        }
        for (int i = 0; i <= degree(); i++) {
            if (coefficient(i) != p.coefficient(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < degree(); i++) {
            sb.append(coefficient(i)).append("x^").append(i).append(" + ");
        }
        sb.append(coefficient(degree())).append("x^").append(degree());
        return sb.toString();
    }
}
