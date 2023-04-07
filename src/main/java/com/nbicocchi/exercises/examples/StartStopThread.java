package com.nbicocchi.exercises.examples;

public class StartStopThread extends Thread {
    public boolean running = true;

    public StartStopThread(String name) {
        super(name);
    }

    public static void main(String[] args) throws InterruptedException {
        StartStopThread a = new StartStopThread("Homer");
        StartStopThread b = new StartStopThread("Marge");

        /* start children threads */
        a.start();
        b.start();

        /* wait 1/10 of second */
        Thread.sleep(100L);

        /* gracefully shut down children threads */
        a.running = false;
        b.running = false;

        /* wait for children before exit */
        a.join();
        b.join();
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
