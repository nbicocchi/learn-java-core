package com.nbicocchi.exercises.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckIntervalBetweenDatesAltTest {
    @Test
    void checkInterval_alternative() {
        assertTrue(CheckIntervalBetweenDatesAlt.checkIntervalBetweenDatesAlt("2022-12-15", "2022-12-17"));
        assertFalse(CheckIntervalBetweenDatesAlt.checkIntervalBetweenDatesAlt("2022-12-15", "2021-12-15"));
        assertFalse(CheckIntervalBetweenDatesAlt.checkIntervalBetweenDatesAlt("15/12/2022", "17/12/1922"));
    }
}
