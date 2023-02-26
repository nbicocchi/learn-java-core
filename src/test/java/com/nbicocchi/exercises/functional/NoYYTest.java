package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoYYTest {
    @Test
    void noYY() {
        assertEquals(List.of("ay", "by", "cy"), NoYY.noYY(List.of("a", "b", "c")));
        assertEquals(List.of("ay", "by"), NoYY.noYY(List.of("a", "b", "cy")));
        assertEquals(List.of("xxy", "yay", "zzy"), NoYY.noYY(List.of("xx", "ya", "zz")));
    }
}