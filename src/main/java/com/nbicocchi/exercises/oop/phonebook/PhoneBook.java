package com.nbicocchi.exercises.oop.phonebook;

import com.nbicocchi.exercises.oop.common.Person;

/**
 * Interface representing a generic PhoneBook Implementing classes must provide
 * methods for inserting, deleting and searching persons within the PhoneBook
 *
 * @author Nicola Bicocchi
 */
public interface PhoneBook {
    /**
     * Add a person to the PhoneBook
     *
     * @param p The person to be added to the PhoneBook
     */
    void addPerson(Person p);

    /**
     * Search a person within the PhoneBook by name
     *
     * @param name The name to be searched
     *
     * @return The person found, null otherwise
     */
    Person searchByName(String name);

    /**
     * Search a person within the PhoneBook by lastname
     *
     * @param lastname The lastname to be searched
     *
     * @return The person found, null otherwise
     */
    Person searchByLastname(String lastname);

    /**
     * Search a person within the PhoneBook by number
     *
     * @param phone The phone to be searched
     *
     * @return The person found, null otherwise
     */
    Person searchByNumber(String phone);

    /**
     * Delete a person from the PhoneBook
     *
     * @param phone The phone number to be searched.
     */
    void deleteByNumber(String phone);
}
