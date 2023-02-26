package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseTest {

    @Test
    void reverse() {
        assertEquals(List.of("am", "I"), Reverse.reverse(List.of("I", "am")));
        assertEquals(List.of("1", "2", "3"), Reverse.reverse(List.of("3", "2", "1")));
    }
}