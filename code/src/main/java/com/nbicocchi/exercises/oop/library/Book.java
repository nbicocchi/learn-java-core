package com.nbicocchi.exercises.oop.library;

public class Book extends Item {
    int pages;

    public Book(String title, int year, int pages) {
        super(title, year);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" + "pages=" + pages + ", title='" + title + '\'' + ", year=" + year + '}';
    }
}
