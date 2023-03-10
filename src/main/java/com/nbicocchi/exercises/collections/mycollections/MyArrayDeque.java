package com.nbicocchi.exercises.collections.mycollections;

public class MyArrayDeque extends MyArrayList implements MyDeque {

    @Override
    public void addFirst(Object o) {
        add(o, 0);
    }

    @Override
    public Object removeFirst() {
        return remove(0);
    }

    @Override
    public void addLast(Object o) {
        add(o);
    }

    @Override
    public Object removeLast() {
        return remove(size - 1);
    }
}
