package com.nbicocchi.exercises.exceptions;

import java.time.LocalDate;

public class CheckIntervalBetweenDates {
    public static boolean checkIntervalBetweenDates(String begin, String end) {
        LocalDate dateBegin = LocalDate.parse(begin);
        LocalDate dateEnd = LocalDate.parse(end);
        return dateBegin.isBefore(dateEnd);
    }
}
