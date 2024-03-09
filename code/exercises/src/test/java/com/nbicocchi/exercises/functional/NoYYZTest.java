package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoYYZTest {
    @Test
    void noYY() {
        assertEquals(List.of("ay", "by", "cy"), NoYYZ.noYYZ(List.of("a", "b", "c")));
        assertEquals(List.of("ay", "by"), NoYYZ.noYYZ(List.of("a", "b", "cy")));
        assertEquals(List.of("xxy", "yay", "zzy"), NoYYZ.noYYZ(List.of("xx", "ya", "zz")));
    }
}