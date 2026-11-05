package com.nbicocchi.addressbook.model;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(long id) {
        super("Contact " + id + " does not exist");
    }
}
