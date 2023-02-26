package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorTest {
    @Test
    public void sort() {
        Vector v = new Vector(new int[]{1, 5, 55, 3, 88});
        v.sort();
        assertArrayEquals(new int[]{1, 3, 5, 55, 88}, v.getV());
    }

    @Test
    public void search() {
        Vector v = new Vector(new int[]{1, 5, 55, 3, 88});
        assertEquals(0, v.search(1));
        assertEquals(4, v.search(88));
        assertEquals(-1, v.search(2));
    }

    @Test
    public void show() {
        Vector v = new Vector(new int[]{1, 5, 55, 3, 88});
        v.sort();
        assertEquals("[1, 3, 5, 55, 88, ]", v.show());
    }
}