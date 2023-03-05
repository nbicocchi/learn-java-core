package com.nbicocchi.exercises.oop.library;

import java.time.LocalDate;
import java.util.ArrayList;

public class Library {
    final ArrayList<Rent> rents;

    public Library() {
        rents = new ArrayList<>();
    }

    public boolean addRent(Rent rent) {
        return rents.add(rent);
    }

    public boolean removeRent(Rent rent) {
        return rents.remove(rent);
    }

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
