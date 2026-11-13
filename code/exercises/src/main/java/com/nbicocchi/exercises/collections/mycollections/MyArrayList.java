package com.nbicocchi.exercises.collections.mycollections;

/**
 * Implementation of a simplified ArrayList
 *
 * @author Nicola Bicocchi
 */
public class MyArrayList extends MyAbstractList {
    private static final int INITIAL_SIZE = 16;
    private Object[] elements;

    public MyArrayList() {
        super();
        elements = new Object[INITIAL_SIZE];
    }

    void enlarge() {
        Object[] tmp = new Object[elements.length * 2];
        System.arraycopy(elements, 0, tmp, 0, elements.length);
        elements = tmp;
    }

    @Override
    public void add(Object o) {
        if (size >= elements.length - 1) {
            enlarge();
        }
        elements[size++] = o;
    }

    @Override
    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                remove(i);
                return;
            }
        }
    }

    @Override
    public void add(Object o, int index) {
        checkBoundaries(index, size);
        add(o);
        if (elements.length - 1 - index >= 0) {
            System.arraycopy(elements, index, elements, index + 1, elements.length - 1 - index);
        }
        elements[index] = o;
        elements[size] = null;
    }

    @Override
    public Object remove(int index) {
        checkBoundaries(index, size - 1);
        Object removed = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index);
        elements[--size] = null;
        return removed;
    }

    @Override
    public Object get(int index) {
        checkBoundaries(index, size - 1);
        return elements[index];
    }
}
