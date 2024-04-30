package com.nbicocchi.exercises.warmup;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public class GuessNumber {

    public static void main(String[] args) {
        RandomGenerator rng = RandomGenerator.getDefault();
        Scanner scanner = new Scanner(System.in);

        for (;;) {
            int number = rng.nextInt(3) + 1;
            System.out.print("--> I'm thinking a number between 1 and 3. Try to guess which one! ");
            int answer;
            try {
                answer = scanner.nextInt();
            } catch (NoSuchElementException | IllegalStateException ex) {
                scanner.nextLine();
                ex.printStackTrace();
                continue;
            }
            if (answer == 0) {
                System.out.println("[I knew we were tightly connected! Bye!]");
                break;
            } else if (number != answer) {
                System.out.println("[Wrong answer!]");
            } else {
                System.out.println("[Correct answer!]");
            }
        }
    }
}
