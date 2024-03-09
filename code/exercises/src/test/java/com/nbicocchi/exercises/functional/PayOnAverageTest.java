package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayOnAverageTest {
    @Test
    void payMore() {
        Map<String, Double> fees = new HashMap<>(Map.of(
                "3474565432", 11.75,
                "3336534965", 9.95,
                "3315467927", 9.75,
                "3316393736", 8.75));
        assertEquals(Map.of(
                "331", 9.25,
                "333", 9.95,
                "347", 11.75), PayOnAverage.payOnAverage(fees));
    }
}