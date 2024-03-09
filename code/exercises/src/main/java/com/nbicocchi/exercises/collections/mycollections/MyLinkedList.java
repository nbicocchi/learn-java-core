package com.nbicocchi.exercises.collections.mycollections;

/**
 * Implementation of a simplified LinkedList class
 *
 * @author Nicola Bicocchi
 */
public class MyLinkedList extends MyAbstractList implements MyDeque {
    private Node head;

    public MyLinkedList() {
        super();
        head = null;
    }

    @Override
    public void add(Object o) {
        add(o, size);
    }

    @Override
    public void add(Object o, int index) {
        checkBoundaries(index, size);
        if (index == 0) {
            head = new Node(o, head);
        } else {
            Node current = getNodeByIndex(index - 1);
            current.setNext(new Node(o, current.getNext()));
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        Object removed;
        checkBoundaries(index, size - 1);
        if (index == 0) {
            removed = head.getPayload();
            head = head.getNext();
        } else {
            Node previous = getNodeByIndex(index - 1);
            removed = previous.getNext().getPayload();
            previous.setNext(previous.getNext().getNext());
        }
        size--;
        return removed;
    }

    @Override
    public Object get(int index) {
        checkBoundaries(index, size - 1);
        return getNodeByIndex(index).getPayload();
    }

    private Node getNodeByIndex(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    @Override
    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            Node node = getNodeByIndex(i);
            if (node.getPayload().equals(o)) {
                remove(i);
                return;
            }
        }
    }

    @Override
    public void addFirst(Object o) {
        add(o, 0);
    }

    @Override
    public Object removeFirst() {
        Object payload = get(0);
        remove(0);
        return payload;
    }

    @Override
    public void addLast(Object o) {
        add(o);
    }

    @Override
    public Object removeLast() {
        Object payload = get(size - 1);
        remove(size - 1);
        return payload;
    }

    /**
     * Implementation of a single node composing the linked list
     *
     * @author Nicola Bicocchi
     */
    private static class Node {
        private Object payload;
        private Node next;

        public Node(Object payload, Node next) {
            this.payload = payload;
            this.next = next;
        }

        public Object getPayload() {
            return payload;
        }

        public void setPayload(Object payload) {
            this.payload = payload;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}