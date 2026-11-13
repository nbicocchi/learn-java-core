package com.nbicocchi.tutorials.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionAtomic {
    static int times = 10000;
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                atomicInteger.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                atomicInteger.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + atomicInteger.get()); // Expected: 20000, Actual: often less
    }
}
