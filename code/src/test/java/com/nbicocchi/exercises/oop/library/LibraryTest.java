package com.nbicocchi.exercises.oop.library;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void addRent() {
        Library library = new Library();
        Rent r1 = new Rent(
                new Book("Effective Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 1));
        assertTrue(library.addRent(r1));
    }

    @Test
    void removeRent() {
        Library library = new Library();
        Rent r1 = new Rent(
                new Book("Effective Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 1));
        Rent r2 = new Rent(
                new Book("Effective Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 1));
        library.addRent(r1);
        library.addRent(r2);
        assertTrue(library.removeRent(r2));
    }

    @Test
    void getExpired() {
        Library library = new Library();
        Rent r1 = new Rent(
                new Book("Effective Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 1));
        Rent r2 = new Rent(
                new Book("Effective Java", 2019, 1350),
                new Person("ID1456", "Nicola", "Bicocchi"),
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 7, 1));
        library.addRent(r1);
        library.addRent(r2);
        assertEquals(1, library.getExpired(LocalDate.of(2023, 4, 1)).size());
    }
}