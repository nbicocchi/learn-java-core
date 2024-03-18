package com.nbicocchi.tutorials.oop.company;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammerTest {
    @Test
    public void createProgrammerTest() {
        Programmer programmer = new Programmer(
                "John Doe",
                LocalDate.of(2000, 10, 6),
                "Rodeo Drive",
                LocalDate.of(2024, 3, 5),
                500_000L,
                new String[]{"Java", "Kotlin", "C"}
        );
        assertNotNull(programmer);
    }

}