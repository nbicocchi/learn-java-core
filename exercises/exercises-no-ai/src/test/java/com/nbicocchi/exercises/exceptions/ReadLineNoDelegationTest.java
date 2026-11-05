package com.nbicocchi.exercises.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ReadLineNoDelegationTest {
    @Test
    void noDelegation() {
        assertNull(ReadLineNoDelegation.readLineNoDelegation("src/main/resources/missing-file.txt"));
        assertEquals("aahing",ReadLineNoDelegation.readLineNoDelegation("src/main/resources/english-words.txt"));
    }
}
