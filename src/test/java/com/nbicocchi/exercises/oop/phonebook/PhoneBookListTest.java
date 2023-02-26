package com.nbicocchi.exercises.oop.phonebook;

import com.nbicocchi.exercises.oop.common.Person;
import org.junit.jupiter.api.BeforeEach;

class PhoneBookListTest extends PhoneBookTestBase {
    @BeforeEach
    void setUp() {
        pb = new PhoneBookList();
        pb.addPerson(new Person("Nicola", "Bicocchi", "34567"));
        pb.addPerson(new Person("Marco", "Rizzo", "45243"));
        pb.addPerson(new Person("Luisa", "Poppi", "24564"));
    }
}