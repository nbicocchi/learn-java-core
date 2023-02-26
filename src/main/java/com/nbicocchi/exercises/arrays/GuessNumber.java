package com.nbicocchi.exercises.arrays;

import java.util.Arrays;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public class GuessNumber {
    public static final int MAX_ANSWERS = 10;

    public static void main(String[] args) {
        int[] answers = guessNumber();
        System.out.println(Arrays.toString(answers));
    }

    public static int[] guessNumber() {
        int[] answers = new int[MAX_ANSWERS];
        RandomGenerator rng = RandomGenerator.getDefault();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < MAX_ANSWERS; i++) {
            int number = rng.nextInt(6);
            System.out.print("--> I'm thinking a number between 1 and 5. Try to guess which one! ");
            int answer = scanner.nextInt();
            if (number == answer) {
                answers[i] = 1;
                System.out.println("[I knew we were tightly connected!]");
            }
        }
        return answers;
    }
}
