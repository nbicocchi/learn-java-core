package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecurringCharsTest {

    @Test
    void recurringChars() {
        assertEquals(Set.of(), RecurringChars.recurringChars("Ciao"));
        assertEquals(Set.of('l'), RecurringChars.recurringChars("Hello"));
        assertEquals(Set.of('l', 'o'), RecurringChars.recurringChars("Hello World!"));
    }
}