package com.nbicocchi.exercises.threads;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class DayChangeCheck extends Thread {
    public DayChangeCheck(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " started");
        LocalDate lastCheck = LocalDate.now();
        while (!interrupted()) {
            System.out.println("Today is " + lastCheck);
            try {
                Thread.sleep(TimeUnit.MINUTES.toMillis(15));
            } catch (InterruptedException ignored) {

            }

            LocalDate now = LocalDate.now();
            if (now.isAfter(lastCheck)) {
                System.out.println(getName() + " the day of month changed");
                lastCheck = now;
            }
        }
        System.out.println(getName() + " terminated");
    }

    public static void main(String[] args) {
        DayChangeCheck t1 = new DayChangeCheck("DayChangeCheck");
        t1.start();
    }
}