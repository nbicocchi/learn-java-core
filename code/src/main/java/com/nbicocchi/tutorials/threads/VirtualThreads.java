package com.nbicocchi.tutorials.threads;

import java.time.Instant;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreads {
    private final AtomicInteger atomicInteger = new AtomicInteger();

    private Runnable getRunnable() {
        return () -> {
            try {
                Thread.sleep(Duration.ofMillis(1000));
            } catch(Exception e) {
                System.out.println(e);
            }
            System.out.println("Work Done - " + atomicInteger.incrementAndGet());
        };
    }

    private long executePlatformThreads(Runnable runnable) {
        Instant start = Instant.now();

        try (ExecutorService executor = Executors.newFixedThreadPool(100)) {
            for(int i = 0; i < 10000; i++) {
                executor.submit(runnable);
            }
        }

        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    private long executeVirtualThreads(Runnable runnable) {
        Instant start = Instant.now();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for(int i = 0; i < 10_000; i++) {
                executor.submit(runnable);
            }
        }

        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    public void execute() {
        String str = String.format("Elapsed time: %d ms",
                //executePlatformThreads(getRunnable()));
                executeVirtualThreads(getRunnable()));
        System.out.println(str);
    }

    public static void main(String[] args) {
        VirtualThreads vt = new VirtualThreads();
        vt.execute();
    }
}
