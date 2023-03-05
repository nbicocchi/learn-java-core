package com.nbicocchi.exercises.oop.phonebook;

/**
 * Interface representing a generic PhoneBook Implementing classes must provide
 * methods for inserting, deleting and searching persons within the PhoneBook
 *
 * @author Nicola Bicocchi
 */
public interface PhoneBook {
    /**
     * Adds a person to the phone book
     *
     * @param person The person to be added to the phone book
     */
    void addPerson(Person person);

    /**
     * Deletes a person from the phone book
     *
     * @param person The person to be removed from the phone book
     */
    void removePerson(Person person);

    /**
     * Searches a person within the phone book by name
     *
     * @param name The name to be searched
     *
     * @return The person found, null otherwise
     */
    Person searchByName(String name);

    /**
     * Searches a person within the phone book by lastname
     *
     * @param lastname The lastname to be searched
     *
     * @return The person found, null otherwise
     */
    Person searchByLastname(String lastname);

    /**
     * Searches a person within the phone book by number
     *
     * @param phone The phone to be searched
     *
     * @return The person found, null otherwise
     */
    Person searchByNumber(String phone);
}
