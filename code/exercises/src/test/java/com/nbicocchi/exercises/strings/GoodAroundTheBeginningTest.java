package com.nbicocchi.exercises.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoodAroundTheBeginningTest {
    @Test
    public void goodAroundTheBeginning() {
        assertTrue(GoodAroundTheBeginning.goodAroundTheBeginning("good Sweet"));
        assertTrue(GoodAroundTheBeginning.goodAroundTheBeginning(" good Sweet"));
        assertFalse(GoodAroundTheBeginning.goodAroundTheBeginning("goo"));
    }
}
