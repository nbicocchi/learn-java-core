package com.nbicocchi.exercises.examples;

public class StartStopThread extends Thread {
    public boolean running = true;

    public StartStopThread(String name) {
        super(name);
    }

    public static void main(String[] args) {
        StartStopThread a = new StartStopThread("Homer");
        StartStopThread b = new StartStopThread("Marge");
        a.setPriority(Thread.MAX_PRIORITY);
        b.setPriority(Thread.MIN_PRIORITY);
        a.start();
        b.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        // Graceful shutdown!
        a.running = false;
        b.running = false;
        // Ungraceful shutdown!
        // a.interrupt();
        // b.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started");
        while (running) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " terminated");
    }
}
