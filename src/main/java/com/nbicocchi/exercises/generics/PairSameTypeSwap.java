package com.nbicocchi.exercises.generics;

/**
 * Class representing a pair of elements of different types.
 *
 * @param <T> The type of the two elements
 */
public class PairSameTypeSwap<T> {
    T first;
    T second;

    public PairSameTypeSwap(T first, T second) {
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
