package com.nbicocchi.exercises.e2_basics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoodAtTheBeginningTest {
    @Test
    public void goodAtTheBeginning() {
        assertTrue(GoodAtTheBeginning.goodAtTheBeginning("good Sweet"));
        assertFalse(GoodAtTheBeginning.goodAtTheBeginning(" good Sweet"));
        assertFalse(GoodAtTheBeginning.goodAtTheBeginning("goo"));
    }
}
