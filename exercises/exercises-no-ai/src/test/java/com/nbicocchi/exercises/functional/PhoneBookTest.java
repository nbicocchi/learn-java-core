package com.nbicocchi.exercises.functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneBookTest {
    PhoneBook pbook;

    @BeforeEach
    void setUp() {
        List<PhoneBook.Person> people = new ArrayList<>(List.of(
                new PhoneBook.Person("nicola", "bicocchi", "3478001122"),
                new PhoneBook.Person("marzia", "neri","3335566777"),
                new PhoneBook.Person("erica", "risi","32d8123456")
        ));
        pbook = new PhoneBook(people);
    }

    @Test
    void searchByLastname() {
        assertEquals(Optional.empty(), pbook.searchByLastname("bianchi"));
        assertEquals(Optional.of(new PhoneBook.Person("marzia", "neri","3335566777")),
                pbook.searchByLastname("neri"));
    }

    @Test
    void searchByNameAndLastname() {
        assertEquals(Optional.empty(), pbook.searchByNameAndLastname("marzia","bianchi"));
        assertEquals(Optional.of(new PhoneBook.Person("marzia", "neri","3335566777")),
                pbook.searchByNameAndLastname("marzia", "neri"));
    }
}