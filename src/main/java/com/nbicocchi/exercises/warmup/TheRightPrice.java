package com.nbicocchi.exercises.warmup;

import java.util.Scanner;
import java.util.random.RandomGenerator;

public class TheRightPrice {
    public static void main(String[] args) {
        RandomGenerator rnd = RandomGenerator.getDefault();
        int rightPrice = rnd.nextInt(101);
        Scanner scanner = new Scanner(System.in);
        System.out.print("(a) Your guess for the right price [0, 100] ? ");
        int aPrice = scanner.nextInt();
        System.out.print("(b) Your guess for the right price [0, 100] ? ");
        int bPrice = scanner.nextInt();
        if (Math.abs(rightPrice - aPrice) < Math.abs(rightPrice - bPrice)) {
            System.out.println("(a) wins the item!");
        } else if (Math.abs(rightPrice - aPrice) > Math.abs(rightPrice - bPrice)){
            System.out.println("(b) wins the item!");
        } else {
            System.out.println("(a) and (b) win the item!");
        }
    }
}
