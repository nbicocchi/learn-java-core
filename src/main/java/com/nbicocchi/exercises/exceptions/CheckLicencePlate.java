package com.nbicocchi.exercises.exceptions;

public class CheckLicencePlate {
    public static void checkIfMadeOfLetters(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new IllegalArgumentException("Not a letter: " + c);
            }
        }
    }

    public static void checkIfMadeOfDigits(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Not a digit: " + c);
            }
        }
    }

    public static void checkLicencePlate(String licence) {
        if (licence.length() != 7) {
            throw new IllegalArgumentException("Length != 7");
        }
        checkIfMadeOfLetters(licence.substring(0, 2));
        checkIfMadeOfDigits(licence.substring(2, 5));
        checkIfMadeOfLetters(licence.substring(5, 7));
    }
}
