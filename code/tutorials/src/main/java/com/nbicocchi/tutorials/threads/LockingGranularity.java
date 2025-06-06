package com.nbicocchi.tutorials.threads;

import java.time.Duration;

public class LockingGranularity {
    static class Actor extends Thread {
        Runnable runnable;

        public Actor(Runnable runnable) {
            super();
            this.runnable = runnable;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                runnable.run();
            }
        }
    }

    static class SharedResource {
        public synchronized void A() {
            System.out.println(Thread.currentThread().getName() + " A()");
            try {
                Thread.sleep(Duration.ofMillis(100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public synchronized void B() {
            System.out.println(Thread.currentThread().getName() + " B()");
        }

        public void C() {
            System.out.println(Thread.currentThread().getName() + " C()");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SharedResource resource = new SharedResource();
        Actor a = new Actor(resource::A);
        Actor b = new Actor(resource::B);
        Actor c = new Actor(resource::C);

        a.start();
        b.start();
        c.start();

        Thread.sleep(2);

        a.interrupt();
        b.interrupt();
        c.interrupt();

        a.join();
        b.join();
        c.join();
    }
}
