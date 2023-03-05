package com.nbicocchi.exercises.oop.library;

import java.time.LocalDateTime;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Rent rent = (Rent) o;
        return Objects.equals(item, rent.item) && Objects.equals(person, rent.person) && Objects.equals(begin, rent.begin) && Objects.equals(end, rent.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, person, begin, end);
    }

    @Override
    public String toString() {
        return "Rent{" + "item=" + item + ", person=" + person + ", begin=" + begin + ", end=" + end + '}';
    }
}
