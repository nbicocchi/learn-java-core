package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseTest {

    @Test
    void reverseCharAtUsingStringBuilder() {
        assertEquals(List.of("am", "I"), copyAndReverseCharAtUsingStringBuilder(List.of("I", "am")));
        assertEquals(List.of("1", "2", "3"), copyAndReverseCharAtUsingStringBuilder(List.of("3", "2", "1")));
        assertEquals(List.of(), copyAndReverseCharAtUsingStringBuilder(List.of()));
    }

    private List<String> copyAndReverseCharAtUsingStringBuilder(List<String> sentence) {
        List<String> reversed = new ArrayList<>(sentence);
        Reverse.reverse(reversed);
        return reversed;
    }
}