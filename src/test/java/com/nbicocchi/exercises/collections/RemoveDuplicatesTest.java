package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveDuplicatesTest {

    @Test
    void removeDuplicates() {
        assertEquals(List.of(1, 2, 3, 4, 5), RemoveDuplicates.removeDuplicates(List.of(1, 2, 3, 4, 5)));
        assertEquals(List.of(1, 2), RemoveDuplicates.removeDuplicates(List.of(1, 1, 1, 2, 2)));
    }
}