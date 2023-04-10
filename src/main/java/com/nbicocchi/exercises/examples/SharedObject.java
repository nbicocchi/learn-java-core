package com.nbicocchi.exercises.examples;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.random.RandomGenerator;

public class SharedObject {
    static class ProducerSafe extends Thread {
        final Deque<Integer> integerDeque;

        public ProducerSafe(Deque<Integer> integerDeque) {
            super();
            this.integerDeque = integerDeque;
        }

        @Override
        public void run() {
            RandomGenerator rnd = RandomGenerator.getDefault();
            while (!isInterrupted()) {
                synchronized (integerDeque) {
                    integerDeque.addFirst(rnd.nextInt());
                }
            }
        }
    }

    static class ProducerUnsafe extends Thread {
        final Deque<Integer> integerDeque;

        public ProducerUnsafe(Deque<Integer> integerDeque) {
            super();
            this.integerDeque = integerDeque;
        }

        @Override
        public void run() {
            RandomGenerator rnd = RandomGenerator.getDefault();
            while (!isInterrupted()) {
                integerDeque.addFirst(rnd.nextInt());
            }
        }
    }

    static class ConsumerSafe extends Thread {
        final Deque<Integer> integerDeque;

        public ConsumerSafe(Deque<Integer> integerDeque) {
            super();
            this.integerDeque = integerDeque;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    synchronized (integerDeque) {
                        integerDeque.removeLast();
                    }
                } catch (NoSuchElementException e) {
                    Thread.yield();
                }
            }
        }
    }

    static class ConsumerUnsafe extends Thread {
        final Deque<Integer> integerDeque;

        public ConsumerUnsafe(Deque<Integer> integerDeque) {
            super();
            this.integerDeque = integerDeque;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    integerDeque.removeLast();
                } catch (NoSuchElementException e) {
                    Thread.yield();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /* An unsafe shared object require safe threads and vice versa */
        Deque<Integer> dq = new LinkedList<>();
        ProducerSafe p = new ProducerSafe(dq);
        ConsumerSafe c = new ConsumerSafe(dq);

        p.start();
        c.start();

        Thread.sleep(100L);

        p.interrupt();
        c.interrupt();

        p.join();
        c.join();
    }
}
