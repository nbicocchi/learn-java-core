package com.nbicocchi.exercises.oop.phonebook;

import com.nbicocchi.exercises.oop.common.Person;

import java.util.ArrayList;

/**
 * A PhoneBook implementation internally using ArrayList. Slow!
 *
 * @author Nicola Bicocchi
 * @see PhoneBook
 */
public class PhoneBookList implements PhoneBook {
    final ArrayList<Person> phoneBook;

    public PhoneBookList() {
        phoneBook = new ArrayList<>();
    }

    @Override
    public void addPerson(Person p) {
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
        if (p != null)
            phoneBook.remove(p);
    }
}
