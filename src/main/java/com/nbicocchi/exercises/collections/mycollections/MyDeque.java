package com.nbicocchi.exercises.collections.mycollections;

public interface MyDeque extends MyCollection {
    void addFirst(Object o);
    Object removeFirst();
    void addLast(Object o);
    Object removeLast();
}
