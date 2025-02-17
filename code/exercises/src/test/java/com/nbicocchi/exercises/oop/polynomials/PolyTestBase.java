package com.nbicocchi.exercises.oop.polynomials;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class PolyTestBase {
    Poly p;

    @Test
    void testEquals() {
        Poly pa = new ArrayPoly(new double[]{1, 2, 3, 4});
        Poly pb = new ArrayPoly(new double[]{1, 2, 3, 5});
        Poly pc = new ListPoly(new double[]{1, 2, 3, 4});
        Poly pd = new ListPoly(new double[]{1, 2, 3, 5});
        assertEquals(p, pa);
        assertEquals(p, pc);
        assertNotEquals(p, pb);
        assertNotEquals(p, pd);
    }

    @Test
    void degree() {
        assertEquals(3, p.degree());
    }

    @Test
    void derivative() {
        assertEquals(new ArrayPoly(new double[]{2, 6, 12}), p.derivative());
    }

    @Test
    void coefficient() {
        assertEquals(1, p.coefficient(0));
        assertEquals(2, p.coefficient(1));
        assertEquals(3, p.coefficient(2));
        assertEquals(4, p.coefficient(3));
    }

    @Test
    void coefficients() {
        assertArrayEquals(new double[]{1, 2, 3, 4}, p.coefficients());
    }
}