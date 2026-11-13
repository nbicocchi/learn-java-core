package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class JustPrintTest {

    @Test
    void justPrintList() {
        JustPrint.justPrintList(List.of("Marco", "Matteo", "Luca", "Giovanni"));
    }

    @Test
    void justPrintMap() {
        JustPrint.justPrintMap(Map.of(1, "nicola", 2, "agata", 3, "darma", 4, "marzia"));
    }
}