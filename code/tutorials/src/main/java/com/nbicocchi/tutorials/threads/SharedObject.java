package com.nbicocchi.tutorials.threads;

import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SharedObject {

    /**
     * An unsafe producer, putting elements on a shared queue without synchronization
     */
    static class ProducerUnsafe extends Thread {
        final Deque<Integer> integerDeque;

        public ProducerUnsafe(Deque<Integer> integerDeque) {
            super();
            this.integerDeque = integerDeque;
        }

        @Override
        public void run() {
            int i = 0;
            while (!isInterrupted()) {
                integerDeque.addFirst(i++);
            }
        }
    }

    /**
     * A safe producer, putting elements on a shared queue with synchronization
     */
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


    /**
     * A safe producer, putting elements on a shared queue with synchronization
     * and using wait() and notify() for increased performance
     */
    static class ProducerSafeWaitNotify extends Thread {
        final Deque<Integer> integerDeque;
        final int maxDequeSize = 10;

        public ProducerSafeWaitNotify(Deque<Integer> integerDeque) {
            super();
            this.integerDeque = integerDeque;
        }

        @Override
        public void run() {
            int i = 0;
            while (!isInterrupted()) {
                synchronized (integerDeque) {
                    if (integerDeque.size() < maxDequeSize) {
                        integerDeque.addFirst(i++);
                        integerDeque.notifyAll();
                    } else {
                        try {
                            integerDeque.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        }
    }

    /**
     * An unsafe consumer, fetching elements from a queue without synchronization
     */
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
                    System.out.println(integerDeque.removeLast());
                } catch (NoSuchElementException e) {
                    Thread.yield();
                }
            }
        }
    }

    /**
     * A safe consumer, fetching elements from a queue with synchronization
     */
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
                        System.out.println(integerDeque.removeLast());
                    }
                } catch (NoSuchElementException e) {
                    Thread.yield();
                }
            }
        }
    }

    /**
     * A safe consumer, fetching elements from a queue with synchronization
     * and using wait() and notify() for increased performance
     */
    static class ConsumerSafeWaitNotify extends Thread {
        final Deque<Integer> integerDeque;

        public ConsumerSafeWaitNotify(Deque<Integer> integerDeque) {
            super();
            this.integerDeque = integerDeque;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                synchronized (integerDeque) {
                    if (integerDeque.size() > 0) {
                        System.out.println(integerDeque.removeLast());
                        integerDeque.notifyAll();
                    } else {
                        try {
                            integerDeque.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        }
    }


    public static void runExperiment(Thread producer, Thread consumer) throws InterruptedException {
        producer.start();
        consumer.start();

        Thread.sleep(100L);

        producer.interrupt();
        consumer.interrupt();

        producer.join();
        consumer.join();
    }

    public static void main(String[] args) throws InterruptedException {
        /* An unsafe shared object require safe threads */
        //Deque<Integer> dq = new LinkedList<>();
        //Thread p = new ProducerSafeWaitNotify(dq);
        //Thread c = new ConsumerSafeWaitNotify(dq);

        /* A safe shared object do not require safe threads */
        Deque<Integer> dq = new ConcurrentLinkedDeque<>();
        Thread p = new ProducerUnsafe(dq);
        Thread c = new ConsumerUnsafe(dq);

        runExperiment(p, c);
    }
}
