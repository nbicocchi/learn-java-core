package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayMoreTest {
    @Test
    void payMore() {
        Map<String, Double> fees = new HashMap<>(Map.of(
                "3474565432", 11.75,
                "3336534965", 9.95,
                "3315467927", 9.75,
                "3316393736", 8.75));
        PayMore.payMore(fees, "331", 1.0);
        assertEquals(Map.of(
                "3474565432", 11.75,
                "3336534965", 9.95,
                "3315467927", 10.75,
                "3316393736", 9.75), fees);
        PayMore.payMore(fees, "333", -1.0);
        assertEquals(Map.of(
                "3474565432", 11.75,
                "3336534965", 8.95,
                "3315467927", 10.75,
                "3316393736", 9.75), fees);
    }
}