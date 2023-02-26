package com.nbicocchi.exercises.collections.mycollections;

public interface MyList extends MyCollection {
    void add(Object o, int index);

    void remove(int index);

    Object get(int index);
}
