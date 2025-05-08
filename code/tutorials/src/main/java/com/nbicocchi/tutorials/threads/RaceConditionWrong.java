package com.nbicocchi.tutorials.threads;

public class RaceConditionWrong {
    static int times = 10000;
    static int count = 0; // shared variable

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                count++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                count++;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + count); // Expected: 20000, Actual: often less
    }
}

