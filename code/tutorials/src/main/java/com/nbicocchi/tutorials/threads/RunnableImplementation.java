package com.nbicocchi.tutorials.threads;

public class RunnableImplementation {

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started.");

            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " running...");
            }

            System.out.println(Thread.currentThread().getName() + " finisched.");
        }
    }

    public static void main(String[] args) {
        Thread t0 = new Thread(new MyRunnable());
        Thread t1 = new Thread(new MyRunnable());

        //Setting threads priority
        t0.setPriority(Thread.MIN_PRIORITY);
        t1.setPriority(Thread.NORM_PRIORITY);

        //Starting threads (often t1 will start before t0)
        t0.start();
        t1.start();

        try {

            //The father-tread waits 5 milliseconds
            Thread.sleep(10L);

            //Interrupting t1 and t2
            t0.interrupt();
            t1.interrupt();

            //Waiting t1 and t2 end
            t0.join();
            t1.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Father finished!");
    }
}
