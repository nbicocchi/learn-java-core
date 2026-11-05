package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FizzBuzzTest {
    @Test
    void fizzBuzz() {
        assertArrayEquals(new String[]{"1", "2", "Fizz", "4", "Buzz"}, FizzBuzz.fizzBuzz(1, 6));
        assertArrayEquals(new String[]{"1", "2", "Fizz", "4", "Buzz", "Fizz", "7"}, FizzBuzz.fizzBuzz(1, 8));
        assertArrayEquals(new String[]{"1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz"}, FizzBuzz.fizzBuzz(1, 11));
        assertArrayEquals(new String[]{"1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11" , "Fizz" , "13", "14" , "FizzBuzz"}, FizzBuzz.fizzBuzz(1, 16));
        assertArrayEquals(new String[]{"Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz"}, FizzBuzz.fizzBuzz(3, 11));
        assertArrayEquals(new String[]{"Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11" , "Fizz" , "13", "14" , "FizzBuzz"}, FizzBuzz.fizzBuzz(5, 16));
        assertArrayEquals(new String[]{"FizzBuzz", "1", "2", "Fizz"}, FizzBuzz.fizzBuzz(0, 4));
        assertArrayEquals(new String[]{}, FizzBuzz.fizzBuzz(4, 4));
        assertArrayEquals(new String[]{}, FizzBuzz.fizzBuzz(10, 4));
        assertArrayEquals(new String[]{}, FizzBuzz.fizzBuzz(-3, 4));
        assertArrayEquals(new String[]{}, FizzBuzz.fizzBuzz(-4, -2));
    }
}