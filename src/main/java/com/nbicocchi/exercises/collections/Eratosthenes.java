package com.nbicocchi.exercises.collections;

import java.util.HashSet;
import java.util.Set;

public class Eratosthenes {
    public static Set<Integer> eratosthenes(int n) {
        Set<Integer> primes = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            primes.add(i);
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            primes.removeAll(multiples(i, n));
        }
        return primes;
    }

    public static Set<Integer> multiples(int n, int limit) {
        Set<Integer> multiples = new HashSet<>();
        for (int i = 2; n * i <= limit; i++) {
            multiples.add(n * i);
        }
        return multiples;
    }
}
