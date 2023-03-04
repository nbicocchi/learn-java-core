package com.nbicocchi.exercises.oop.phonebook;

/**
 * Interface representing a generic PhoneBook Implementing classes must provide
 * methods for inserting, deleting and searching persons within the PhoneBook
 *
 * @author Nicola Bicocchi
 */
public interface PhoneBook {
    /**
     * Add a person to the phone book
     *
     * @param p The person to be added to the PhoneBook
     */
    void addPerson(Person p);

    /**
     * Search a person within the phone book by name
     *
     * @param name The name to be searched
     *
     * @return The person found, null otherwise
     */
    Person searchByName(String name);

    /**
     * Search a person within the phone book by lastname
     *
     * @param lastname The lastname to be searched
     *
     * @return The person found, null otherwise
     */
    Person searchByLastname(String lastname);

    /**
     * Search a person within the phone book by number
     *
     * @param phone The phone to be searched
     *
     * @return The person found, null otherwise
     */
    Person searchByNumber(String phone);

    /**
     * Delete a person from the phone book
     *
     * @param phone The phone number to be searched.
     */
    void deleteByNumber(String phone);
}
