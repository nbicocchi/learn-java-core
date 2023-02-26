package com.nbicocchi.exercises.exceptions;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckStringTest {
    @Test
    void checkStringTest() {
        assertThrows(ParseException.class, () -> CheckString.checkString("1q2w3e4r5t"));
        assertThrows(ParseException.class, () -> CheckString.checkString("aab3h4z1r4"));
        assertThrows(ParseException.class, () -> CheckString.checkString("a0b3h4z1rr"));
        assertThrows(ParseException.class, () -> CheckString.checkString("a"));
        assertThrows(ParseException.class, () -> CheckString.checkString("0"));
        assertDoesNotThrow(() -> CheckString.checkString("a0b3h4z1r4"));
        assertDoesNotThrow(() -> CheckString.checkString("a0"));
    }
}
