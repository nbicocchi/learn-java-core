package com.nbicocchi.exercises.examples;

public class LockingGranularity {
    static class Actor extends Thread {
        Runnable runnable;
        boolean running = true;

        public Actor(Runnable runnable) {
            super();
            this.runnable = runnable;
        }

        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            while (running) {
                runnable.run();
            }
        }
    }

    static class SharedResource {
        public synchronized void A() {
            System.out.println(Thread.currentThread().getName() + " A()");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void B() {
            System.out.println(Thread.currentThread().getName() + " B()");
        }

        public void C() {
            System.out.println(Thread.currentThread().getName() + " C()");
        }
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Actor a = new Actor(resource::A);
        Actor b = new Actor(resource::B);
        Actor c = new Actor(resource::C);
        a.start();
        b.start();
        c.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.setRunning(false);
        b.setRunning(false);
        c.setRunning(false);
    }
}
