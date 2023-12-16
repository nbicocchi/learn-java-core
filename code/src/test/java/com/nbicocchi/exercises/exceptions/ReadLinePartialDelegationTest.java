package com.nbicocchi.exercises.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReadLinePartialDelegationTest {
    @Test
    void readLinePartialDelegationTest() {
        assertThrows(RuntimeException.class, () -> ReadLinePartialDelegation.readLinePartialDelegation("/tmp/missing"));
    }
}
