package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JustPrintTest {

    @Test
    void justPrint() {
        JustPrint.justPrint(List.of("nicola", "agata", "darma", "marzia"));
    }
}