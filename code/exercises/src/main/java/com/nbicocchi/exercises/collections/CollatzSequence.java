package com.nbicocchi.exercises.collections;

import java.util.*;

public class CollatzSequence {
    public static List<Long> collatzSequence(long n) {
        if (n < 1) {
            return new ArrayList<>();
        }

        List<Long> sequence = new ArrayList<>(List.of(n));
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            sequence.add(n);
        }
        return sequence;
    }
}
