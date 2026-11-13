package com.nbicocchi.exercises.e4_collections.mycollections;

public interface MyMap {
    Object get(Object key);
    void put(Object key, Object value);
    void remove(Object key);
    boolean contains(Object key);
    int size();
}
