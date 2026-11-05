package com.nbicocchi.exercises.threads;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecution {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() ->
                System.out.println(LocalTime.now() + ": Hello!"), 0, 1000, TimeUnit.MILLISECONDS);
    }
}
