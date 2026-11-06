package com.nbicocchi.exercises.e4_collections.mycollections;

public interface MyCollection {
    void add(Object o);
    void remove(Object o);
    MyIterator iterator();
    boolean contains(Object o);
    int size();
}
