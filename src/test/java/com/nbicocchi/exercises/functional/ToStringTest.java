package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToStringTest {

    @Test
    void testToString() {
        assertEquals("1,2,3,4,5", ToString.toString(Stream.of(1, 2, 3, 4, 5), 10));
    }
}