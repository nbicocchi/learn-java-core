package com.nbicocchi.exercises.oop.library;

public class Dvd extends Item {
    int length;

    public Dvd(String title, int year, int length) {
        super(title, year);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Dvd{" + "duration=" + length + ", title='" + title + '\'' + ", year=" + year + '}';
    }
}