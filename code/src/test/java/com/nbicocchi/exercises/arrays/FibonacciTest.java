package com.nbicocchi.exercises.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FibonacciTest {
    @Test
    public void fibonacci() {
        assertArrayEquals(new long[]{1}, Fibonacci.fibonacci(1));
        assertArrayEquals(new long[]{1, 1}, Fibonacci.fibonacci(2));
        assertArrayEquals(new long[]{1, 1, 2, 3, 5}, Fibonacci.fibonacci(5));
    }
}
