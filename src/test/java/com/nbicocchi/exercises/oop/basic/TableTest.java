package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableTest {
    @Test
    public void sum() {
        Table t = new Table(8, 8);
        assertEquals(1296, t.sum(), 0.01);
    }

    @Test
    public void avg() {
        Table t = new Table(8, 8);
        assertEquals(20.25, t.avg(), 0.01);
    }
}