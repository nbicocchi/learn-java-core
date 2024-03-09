package com.nbicocchi.exercises.oop.library;

import java.time.LocalDate;
import java.util.Objects;

public class Rent {
    Item item;
    Person person;
    LocalDate begin;
    LocalDate end;

    public Rent(Item item, Person person, LocalDate begin, LocalDate end) {
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

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    /**
     * Checks if a rent is expired
     * @param now the specified date
     * @return true if the of the rent is before the specified date
     */
    public boolean isExpired(LocalDate now) {
        return end.isBefore(now);
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
