package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollatzSequenceTest {

    @Test
    void collatzSequence() {
        assertIterableEquals(List.of(), CollatzSequence.collatzSequence(0));
        assertIterableEquals(List.of(1L), CollatzSequence.collatzSequence(1));
        assertIterableEquals(List.of(2L, 1L), CollatzSequence.collatzSequence(2));
        assertIterableEquals(List.of(3L, 10L, 5L, 16L, 8L, 4L, 2L, 1L), CollatzSequence.collatzSequence(3));
        assertIterableEquals(List.of(4L, 2L, 1L), CollatzSequence.collatzSequence(4));
    }
}