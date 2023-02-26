package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoTwoTest {
    @Test
    void twoTwo() {
        assertEquals(List.of(4, 6), TwoTwo.twoTwo(List.of(1, 2, 3)));
        assertEquals(List.of(4), TwoTwo.twoTwo(List.of(2, 6, 11)));
        assertEquals(List.of(0), TwoTwo.twoTwo(List.of(0)));
    }
}