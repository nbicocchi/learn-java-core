package com.nbicocchi.exercises.exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CheckIntervalBetweenDatesAlt {
    public static boolean checkIntervalBetweenDatesAlt(String begin, String end) {
        try {
            LocalDate dateBegin = LocalDate.parse(begin);
            LocalDate dateEnd = LocalDate.parse(end);
            return dateBegin.isBefore(dateEnd);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
