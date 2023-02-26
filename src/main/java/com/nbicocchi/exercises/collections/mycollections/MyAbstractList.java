package com.nbicocchi.exercises.collections.mycollections;

public abstract class MyAbstractList implements MyList {
    int size;

    public MyAbstractList() {
        this.size = 0;
    }

    void checkBoundaries(int index, int limit) {
        if (index < 0 || index > limit) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(o)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            sb.append(String.format("%s, ", get(i).toString()));
        }
        sb.append("]");
        return sb.toString();
    }
}
