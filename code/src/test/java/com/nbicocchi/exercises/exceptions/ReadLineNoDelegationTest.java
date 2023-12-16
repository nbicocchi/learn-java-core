package com.nbicocchi.exercises.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ReadLineNoDelegationTest {
    @Test
    void noDelegation() {
        assertNull(ReadLineNoDelegation.readLineNoDelegation("/tmp/missing"));
    }
}
