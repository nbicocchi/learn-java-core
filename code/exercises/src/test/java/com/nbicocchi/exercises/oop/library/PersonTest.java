package com.nbicocchi.exercises.oop.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void equalsHashCode() {
        Person p1 = new Person("Hello", "World", "ID123");
        Person p2 = new Person("Hello", "World", "ID123");
        Person p3 = new Person("Hi", "World", "ID123");

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }
}
