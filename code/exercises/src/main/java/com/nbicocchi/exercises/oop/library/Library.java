package com.nbicocchi.exercises.oop.library;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A library, intended as collection of Rents
 */
public class Library {
    final ArrayList<Rent> rents;

    /**
     * Constructs an empty library
     */
    public Library() {
        rents = new ArrayList<>();
    }

    /**
     * Adds a rent to the library
     * @param rent the rent to be added
     * @return true if the list of rents has been modified (see ArrayList.add())
     */
    public boolean addRent(Rent rent) {
        return rents.add(rent);
    }

    /**
     * Removes a rent from the library
     * @param rent the rent to be removed
     * @return true if the list of rents has been modified (see ArrayList.remove())
     */
    public boolean removeRent(Rent rent) {
        return rents.remove(rent);
    }

    /**
     * Returns a list of rents whose end-date is before than a specified date
     * @param now the specified date
     * @return a list of expired rents
     */
    public ArrayList<Rent> getExpired(LocalDate now) {
        ArrayList<Rent> expired = new ArrayList<>();
        for (Rent rent : rents) {
            if (rent.isExpired(now)) {
                expired.add(rent);
            }
        }
        return expired;
    }
}
