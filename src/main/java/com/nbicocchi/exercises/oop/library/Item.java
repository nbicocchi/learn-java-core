package com.nbicocchi.exercises.oop.library;

/**
 * Class generalizing Books and DVDs
 */
public abstract class Item {
    String title;
    int year;

    public Item(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Item{" + "title='" + title + '\'' + ", year=" + year + '}';
    }
}
