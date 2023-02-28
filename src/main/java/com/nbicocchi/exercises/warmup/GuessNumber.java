package com.nbicocchi.exercises.warmup;

import java.util.Scanner;
import java.util.random.RandomGenerator;

public class GuessNumber {

    public static void main(String[] args) {
        RandomGenerator rng = RandomGenerator.getDefault();
        Scanner scanner = new Scanner(System.in);

        for (;;) {
            int number = rng.nextInt(4);
            System.out.print("--> I'm thinking a number between 1 and 3. Try to guess which one! ");
            int answer = scanner.nextInt();
            if (number != answer) {
                System.out.println("[Wrong answer!]");
            } else if (number == answer && number != 0) {
                System.out.println("[Correct answer!]");
            } else {
                System.out.println("[I knew we were tightly connected! Bye!]");
                break;
            }
        }
    }
}
