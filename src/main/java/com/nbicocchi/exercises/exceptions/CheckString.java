package com.nbicocchi.exercises.exceptions;

import java.text.ParseException;

public class CheckString {
    public static void checkString(String s) throws ParseException {
        if (s.length() < 2) {
            throw new ParseException(s, 0);
        }
        boolean waitingLetter = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c) && (waitingLetter)) {
                throw new ParseException(s, i);
            }
            if (Character.isLetter(c) && (!waitingLetter)) {
                throw new ParseException(s, i);
            }
            waitingLetter = !waitingLetter;
        }
    }
}
