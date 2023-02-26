package com.nbicocchi.exercises.exceptions;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class CheckIntervalBetweenDatesTest {
    @Test
    void checkIntervalBetweenDatesTest() {
        assertThrows(DateTimeParseException.class, () -> CheckIntervalBetweenDates.checkIntervalBetweenDates("15-12-2022", "17/12/2022"));
        assertThrows(DateTimeParseException.class, () -> CheckIntervalBetweenDates.checkIntervalBetweenDates("15/12-2022", "17/12-2022"));
        assertThrows(DateTimeParseException.class, () -> CheckIntervalBetweenDates.checkIntervalBetweenDates("15-32-2022", "17/12-20oo"));
        try {
            assertTrue(CheckIntervalBetweenDatesAlt.checkIntervalBetweenDatesAlt("2022-12-15", "2022-12-17"));
            assertFalse(CheckIntervalBetweenDatesAlt.checkIntervalBetweenDatesAlt("2022-12-15", "2021-12-15"));
        } catch (DateTimeParseException ignored) {
        }
    }
}
