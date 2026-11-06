package com.nbicocchi.exercises.e4_collections.mycollections;

public interface MyList extends MyCollection {
    void add(Object o, int index);
    Object remove(int index);
    Object get(int index);
}
