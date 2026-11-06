package com.nbicocchi.exercises.e4_collections.mycollections;

public interface MyDeque extends MyCollection {
    void addFirst(Object o);
    Object removeFirst();
    void addLast(Object o);
    Object removeLast();
}
