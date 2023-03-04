package com.nbicocchi.exercises.oop.phonebook;

/**
 * A PhoneBook implementation internally using an array. Its capacity is limited to 256 persons.
 *
 * @author Nicola Bicocchi
 * @see PhoneBook Interface
 */
public class PhoneBookArray implements PhoneBook {
    public static final int MAX_PERSONS = 256;
    final Person[] phoneBook;

    public PhoneBookArray() {
        this.phoneBook = new Person[MAX_PERSONS];
    }

    @Override
    public void addPerson(Person p) {
        for (int i = 0; i < MAX_PERSONS; i++) {
            if (phoneBook[i] == null) {
                phoneBook[i] = p;
                return;
            }
        }
        throw new IllegalArgumentException("Phonebook is full");
    }

    @Override
    public Person searchByName(String name) {
        for (int i = 0; i < MAX_PERSONS; i++) {
            if (phoneBook[i] != null) {
                if (phoneBook[i].getName().equals(name)) {
                    return phoneBook[i];
                }
            }
        }
        return null;
    }

    @Override
    public Person searchByLastname(String lastname) {
        for (int i = 0; i < MAX_PERSONS; i++) {
            if (phoneBook[i] != null) {
                if (phoneBook[i].getLastname().equals(lastname)) {
                    return phoneBook[i];
                }
            }
        }
        return null;
    }

    @Override
    public Person searchByNumber(String phone) {
        for (int i = 0; i < MAX_PERSONS; i++) {
            if (phoneBook[i] != null) {
                if (phoneBook[i].getPhone().equals(phone)) {
                    return phoneBook[i];
                }
            }
        }
        return null;
    }

    @Override
    public void deleteByNumber(String phone) {
        for (int i = 0; i < MAX_PERSONS; i++) {
            if (phoneBook[i] != null) {
                if (phoneBook[i].getPhone().equals(phone)) {
                    phoneBook[i] = null;
                }
            }
        }
    }
}
