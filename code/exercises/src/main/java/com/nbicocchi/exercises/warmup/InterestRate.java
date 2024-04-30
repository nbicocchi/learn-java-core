package com.nbicocchi.exercises.warmup;

import java.util.Scanner;

public class InterestRate {
    public static void main(String[] args) {
        final double interest = 0.05;
        double balance = 1000;

        int anno;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buongiorno benvenuti alla Di Quinzio's Bank, offriamo il 5% annuo di interessi");
        while (true) {
            try {
                System.out.println("Quanti anni di deposito volete? (max 70)");
                anno = scanner.nextInt();
                if (anno > 70 || anno < 1) {
                    throw new IllegalArgumentException("Il numero deve essere compreso tra 1 e 70");
                }
                System.out.println("Qual è il capitale iniziale? (min 1000 - max 99999.99)");
                balance = scanner.nextDouble();
                if (balance < 1_000 || balance > 99_999.99) {
                    throw new IllegalArgumentException("Il capitale può essere minimo 1000 e massimo 99999.99");
                }
            } catch (Exception ex) {
                scanner.nextLine();
                System.err.println("Inserire valori validi");
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
