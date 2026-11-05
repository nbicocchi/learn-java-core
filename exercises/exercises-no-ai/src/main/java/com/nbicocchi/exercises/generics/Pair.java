package com.nbicocchi.exercises.generics;

/**
 * Class representing a pair of elements of different types.
 *
 * @param <K> The type of the first element
 * @param <V> The type of the second element
 */
public class Pair<K, V> {
    K first;
    V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }
}
