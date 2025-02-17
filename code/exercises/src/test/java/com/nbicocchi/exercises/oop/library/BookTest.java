package com.nbicocchi.exercises.oop.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {
    Book book;

    @BeforeEach
    void setup() {
        book = new Book("Soffocare", 2002, 170);
    }

    @Test
    void getTitle() {
        assertEquals("Soffocare", book.getTitle());
    }

    @Test
    void setTitle() {
        book.setTitle("Soffocare!");
        assertEquals("Soffocare!", book.getTitle());
    }

    @Test
    void getYear() {
        assertEquals(2002, book.getYear());
    }

    @Test
    void setYear() {
        book.setYear(2001);
        assertEquals(2001, book.getYear());
    }

    @Test
    void getPages() {
        assertEquals(170, book.getPages());
    }

    @Test
    void setPages() {
        book.setPages(180);
        assertEquals(180, book.getPages());
    }

    @Test
    void equalsHashCode() {
        Book other  = new Book("Soffocare", 2002, 170);
        assertEquals(book, other);
        assertEquals(book.hashCode(), other.hashCode());
    }
}