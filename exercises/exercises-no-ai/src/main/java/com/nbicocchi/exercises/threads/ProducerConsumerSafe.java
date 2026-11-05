package com.nbicocchi.exercises.threads;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class ProducerConsumerSafe {
    public static void main(String[] args) throws InterruptedException {
        Deque<Integer> dq = new LinkedList<>();
        Thread producer = new ProducerSafe(dq);
        Thread consumer = new ConsumerSafe(dq);

        producer.start();
        consumer.start();

        Thread.sleep(100L);

        producer.interrupt();
        consumer.interrupt();

        producer.join();
        consumer.join();
    }

    static class ProducerSafe extends Thread {
        final Deque<Integer> integerDeque;

        public ProducerSafe(Deque<Integer> integerDeque) {
            super();
            this.integerDeque = integerDeque;
        }

        @Override
        public void run() {
            int i = 0;
            while (!isInterrupted()) {
                synchronized (integerDeque) {
                    integerDeque.addFirst(i++);
                }
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
            int lastElement = 0;
            while (!isInterrupted()) {
                try {
                    synchronized (integerDeque) {
                        lastElement = integerDeque.removeLast();
                    }
                } catch (NoSuchElementException e) {
                    Thread.yield();
                }
            }
            System.out.printf("Total number of consumed elements: %d", lastElement);
        }
    }
}