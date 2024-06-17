package com.nbicocchi.exercises.exceptions;

import java.text.ParseException;

public class CheckString {
    public static void checkString(String s) throws ParseException {
        if (s.length() < 2) {
            throw new ParseException(s, 0);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!Character.isDigit(c) && (i % 2 == 1)) {
                throw new ParseException(s, i);
            }
            if (!Character.isLetter(c) && (i % 2 == 0)) {
                throw new ParseException(s, i);
            }
        }
    }
}
