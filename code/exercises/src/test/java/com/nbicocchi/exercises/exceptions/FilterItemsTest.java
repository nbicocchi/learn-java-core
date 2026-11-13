package com.nbicocchi.exercises.exceptions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterItemsTest {
    @Test
    void filterItemsTest() {
        List<String> src1 = new ArrayList<>(List.of("aab3h4z1r4", "a0b3h4z1rr", "a0b3h4z1r4", "a0"));
        assertEquals(List.of("a0b3h4z1r4", "a0"), FilterItems.filterItems(src1));
        List<String> src2 = new ArrayList<>(List.of("cab3", "a0b3h4z1rr", "w0b7h4z1r4", "", "a0", "a1d2"));
        assertEquals(List.of("w0b7h4z1r4", "a0", "a1d2"), FilterItems.filterItems(src2));
        List<String> src3 = new ArrayList<>(List.of("bab3", "4z1rr5", "F0b7T4R1r4", "A1D2"));
        assertEquals(List.of("F0b7T4R1r4", "A1D2"), FilterItems.filterItems(src3));
        List<String> src4 = new ArrayList<>(List.of("bab3", "4z1", "A1D2e"));
        assertEquals(List.of(), FilterItems.filterItems(src4));
    }
}
