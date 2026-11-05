package com.nbicocchi.exercises.warmup;

public class InterestRate {
    public static void main(String[] args) {
        final double interest = 0.05;
        double balance = 1000;

        balance += balance * interest;
        System.out.println("Balance after 1 year: " + balance);
        balance += balance * interest;
        System.out.println("Balance after 2 years: " + balance);
        balance += balance * interest;
        System.out.println("Balance after 3 years: " + balance);
    }
}
