package com.nbicocchi.tutorials.threads;

public class StartStopRunnable {

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started");

            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " running...");
            }

            System.out.println(Thread.currentThread().getName() + " terminated");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(new MyRunnable());
        Thread t1 = new Thread(new MyRunnable());

        // Setting threads priority
        t0.setPriority(Thread.MIN_PRIORITY);
        t1.setPriority(Thread.NORM_PRIORITY);

        // Starting threads
        t0.start();
        t1.start();

        // main tread waits 10 milliseconds
        Thread.sleep(10L);

        // main thread interrupts child threads
        t0.interrupt();
        t1.interrupt();

        // main thread waits child threads
        t0.join();
        t1.join();

        System.out.println("Father finished!");
    }
}
