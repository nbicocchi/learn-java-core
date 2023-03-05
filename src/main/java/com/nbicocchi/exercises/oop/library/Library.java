package com.nbicocchi.exercises.oop.library;

import java.util.ArrayList;

public class Library {
    final ArrayList<Rent> rents;

    public Library() {
        rents = new ArrayList<>();
    }

    public void addRent(Rent rent) {
        rents.add(rent);
    }

    public void removeRent(Rent rent) {
        rents.remove(rent);
    }

    public ArrayList<Rent> getExpired() {
        ArrayList<Rent> expired = new ArrayList<>();
        for (Rent rent : rents) {
            if (rent.isExpired()) {
                expired.add(rent);
            }
        }
        return expired;
    }

}
