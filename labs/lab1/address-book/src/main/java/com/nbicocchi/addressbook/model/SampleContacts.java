package com.nbicocchi.addressbook.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class SampleContacts {
    private SampleContacts() {
    }

    public static List<Person> create() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Anna", "Rossi", "Via Emilia 12", "41121", "Modena", LocalDate.of(1998, 3, 14)));
        people.add(new Person("Luca", "Bianchi", "Via Roma 8", "00118", "Roma", LocalDate.of(1995, 7, 22)));
        people.add(new Person("Sara", "Verdi", "Via del Porto 5", "40122", "Bologna", LocalDate.of(2001, 11, 6)));
        people.add(new Person("Marco", "Conti", "Corso Italia 27", "56125", "Pisa", LocalDate.of(1992, 1, 30)));
        people.add(new Person("Giulia", "Romano", "Via Mazzini 16", "10123", "Torino", LocalDate.of(2000, 5, 9)));
        people.add(new Person("Paolo", "Ferrari", "Via Garibaldi 3", "16124", "Genova", LocalDate.of(1988, 9, 18)));
        return people;
    }
}
