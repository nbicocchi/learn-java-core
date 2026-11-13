package com.nbicocchi.exercises.threads;

import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

public class ThreadPool {
    public static final int MAX_TASKS = 8;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threadPool = new Thread[MAX_TASKS];
        RandomGenerator rnd = RandomGenerator.getDefault();

        // creating child threads
        for (int task = 0; task < MAX_TASKS; task++) {
            int taskNumber = task;
            threadPool[task] = new Thread(() -> {
                long start = System.nanoTime();
                System.out.printf("START thread=%s task=%d\n",
                        Thread.currentThread().getName(),
                        taskNumber);

                // the thread does something
                try {
                    Thread.sleep(rnd.nextInt(1000));
                } catch (InterruptedException ignored) {

                }

                long stop = System.nanoTime();
                System.out.printf("STOP thread=%s task=%d t=%d ms\n",
                        Thread.currentThread().getName(),
                        taskNumber,
                        TimeUnit.NANOSECONDS.toMillis(stop - start));
            });
        }

        // starting child threads
        for (int i = 0; i < MAX_TASKS; i++) {
            threadPool[i].start();
        }

        // waiting child threads
        for (int i = 0; i < MAX_TASKS; i++) {
            threadPool[i].join();
        }
    }
}