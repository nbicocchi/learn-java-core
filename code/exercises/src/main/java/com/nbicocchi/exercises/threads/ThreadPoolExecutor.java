package com.nbicocchi.exercises.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

public class ThreadPoolExecutor {
    public static final int MAX_TASKS = 8;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        RandomGenerator rnd = RandomGenerator.getDefault();

        // creating child threads
        for (int task = 0; task < MAX_TASKS; task++) {
            int taskNumber = task;
            executor.submit(() -> {
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

        // waiting child threads
        executor.shutdown();
    }
}