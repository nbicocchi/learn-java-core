package com.nbicocchi.exercises.oop.library;

import com.nbicocchi.exercises.oop.common.Person;

import java.time.LocalDateTime;

/**
 * Rent implements a rent of an Item for a delimited time frame
 */
public class Rent {
    Item item;
    Person person;
    LocalDateTime begin;
    LocalDateTime end;

    public Rent(Item item, Person person, LocalDateTime begin, LocalDateTime end) {
        this.item = item;
        this.person = person;
        this.begin = begin;
        this.end = end;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public boolean isExpired() {
        return end.isBefore(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Rent{" + "item=" + item + ", person=" + person + ", begin=" + begin + ", end=" + end + '}';
    }
}
