package com.nbicocchi.exercises.warmup;

import java.util.Scanner;

public class InterestRate {
    public static void main(String[] args) {
        final double interest = 0.05;
        double balance = 1000;

        int anno;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Good morning and welcome to the Di Quinzio's Bank, we offer an annual interest rate of 5%");
        while (true) {
            try {
                System.out.println("How many years of deposit do you want? (max 70)");
                anno = scanner.nextInt();
                if (anno > 70 || anno < 1) {
                    throw new IllegalArgumentException("The range of the number is between 1 and 70");
                }
                System.out.println("What is the initial capital? (min 1000 - max 99999.99)");
                balance = scanner.nextDouble();
                if (balance < 1_000 || balance > 99_999.99) {
                    throw new IllegalArgumentException("The capital can be min 1000 and max 99999.99");
                }
            } catch (Exception ex) {
                scanner.nextLine();
                System.err.println("Insert valid values");
                continue;
            }
            break;
        }

        for (int i = 0; i < anno; i++) {
            balance += balance * interest;
            System.out.println("Balance after " + (i + 1) + " year: " + balance);
        }
    }
}
