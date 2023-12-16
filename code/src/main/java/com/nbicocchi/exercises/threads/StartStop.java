package com.nbicocchi.exercises.threads;

import java.util.concurrent.TimeUnit;

public class StartStop extends Thread {
    public StartStop(String name) {
        super(name);
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        System.out.println(getName() + " started");
        while (!interrupted()) {
            System.out.printf("%s running since %d ms\n", getName(), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime));
        }
        System.out.println(getName() + " terminated");
    }

    public static void main(String[] args) throws InterruptedException {
        StartStop t1 = new StartStop("T1");
        StartStop t2 = new StartStop("T2");

        t1.start();
        t2.start();

        Thread.sleep(100L);

        t1.interrupt();
        t2.interrupt();

        t1.join();
        t2.join();
    }
}