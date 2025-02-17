package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoTeenTest {
    @Test
    void noTeen() {
        assertEquals(List.of(12, 20), NoTeen.noTeen(List.of(12, 13, 19, 20)));
        assertEquals(List.of(1, 1), NoTeen.noTeen(List.of(1, 14, 1)));
        assertEquals(List.of(), NoTeen.noTeen(List.of(15)));
    }
}