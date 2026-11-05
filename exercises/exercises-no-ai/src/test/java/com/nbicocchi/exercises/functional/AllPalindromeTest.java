package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AllPalindromeTest {
    @Test
    void allPalindrome() {
        assertTrue(AllPalindrome.allPalindrome(List.of("kayak", "deed", "noon")));
        assertFalse(AllPalindrome.allPalindrome(List.of("kayak", "hello")));
        assertFalse(AllPalindrome.allPalindrome(List.of("hello", "world")));
    }
}