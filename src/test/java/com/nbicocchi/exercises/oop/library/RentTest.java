package com.nbicocchi.exercises.oop.library;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RentTest {

    @Test
    void isExpired() {
        Rent r1 = new Rent(
                new Book("Effective Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 1));
        Rent r2 = new Rent(
                new Book("Big Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 7, 1));
        assertTrue(r1.isExpired(LocalDate.of(2023, 4, 1)));
        assertFalse(r2.isExpired(LocalDate.of(2023, 4, 1)));
    }

    @Test
    void equalsHashCode() {
        Rent r1 = new Rent(
                new Book("Big Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 7, 1));
        Rent r2 = new Rent(
                new Book("Big Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 7, 1));
        Rent r3 = new Rent(
                new Book("Big Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 1));
        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotEquals(r1.hashCode(), r3.hashCode());
    }
}