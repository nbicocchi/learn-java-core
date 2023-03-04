package com.nbicocchi.exercises.oop.phonebook;

import java.util.ArrayList;

/**
 * A PhoneBook implementation internally using ArrayList. Its capacity is limited to 256 persons.
 *
 * @author Nicola Bicocchi
 * @see PhoneBook Interface
 */
public class PhoneBookList implements PhoneBook {
    public static final int MAX_PERSONS = 256;
    final ArrayList<Person> phoneBook;

    public PhoneBookList() {
        phoneBook = new ArrayList<>();
    }

    @Override
    public void addPerson(Person p) {
        if (phoneBook.size() >= MAX_PERSONS) {
            throw new IllegalArgumentException("Phonebook is full");
        }
        phoneBook.add(p);
    }

    @Override
    public Person searchByName(String name) {
        for (Person p : phoneBook) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Person searchByLastname(String lastname) {
        for (Person p : phoneBook) {
            if (p.getLastname().equals(lastname)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Person searchByNumber(String phone) {
        for (Person p : phoneBook) {
            if (p.getPhone().equals(phone)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void deleteByNumber(String phone) {
        Person p = searchByNumber(phone);
        if (p != null) {
            phoneBook.remove(p);
        }
    }
}
