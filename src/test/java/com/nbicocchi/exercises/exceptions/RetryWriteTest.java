package com.nbicocchi.exercises.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RetryWriteTest {

    @Test
    void writeWithTries() {
        assertThrows(RuntimeException.class, () -> RetryWrite.writeWithTries(3));
    }
}