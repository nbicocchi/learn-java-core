package com.nbicocchi.exercises.generics;

/**
 * Class representing a pair of elements of the same type.
 *
 * @param <T> The type of the two elements
 */
public class PairSameType<T> {
    T first;
    T second;

    public PairSameType(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public void swap() {
        T tmp = first;
        first = second;
        second = tmp;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
