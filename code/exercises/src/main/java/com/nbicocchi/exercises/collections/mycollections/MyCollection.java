package com.nbicocchi.exercises.collections.mycollections;

public interface MyCollection {
    void add(Object o);

    void remove(Object o);

    MyIterator iterator();

    boolean contains(Object o);

    int size();
}
