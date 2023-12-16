package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeTest {
    @Test
    public void isPalindrome() {
        assertFalse(Palindrome.isPalindrome("whistleblower"));
        assertTrue(Palindrome.isPalindrome("radar"));
        assertTrue(Palindrome.isPalindrome("r"));
        assertTrue(Palindrome.isPalindrome(""));
    }
}
