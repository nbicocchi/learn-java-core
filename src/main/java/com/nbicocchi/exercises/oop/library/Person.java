package com.nbicocchi.exercises.oop.library;

public class Person {
    String name;
    String lastname;
    String id;

    public Person(String name, String lastname, String id) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", lastname='" + lastname + '\'' + ", id='" + id + '\'' + '}';
    }
}
