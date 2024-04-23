package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseTest {

    @Test
    void reverse() {
        assertEquals(List.of("am", "I"), copyAndReverse(List.of("I", "am")));
        assertEquals(List.of("1", "2", "3"), copyAndReverse(List.of("3", "2", "1")));
        assertEquals(List.of(), copyAndReverse(List.of()));
    }

    private List<String> copyAndReverse(List<String> sentence) {
        List<String> reversed = new ArrayList<>(sentence);
        Reverse.reverse(reversed);
        return reversed;
    }
}