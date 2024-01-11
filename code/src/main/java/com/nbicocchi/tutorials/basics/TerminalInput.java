package com.nbicocchi.tutorials.basics;

import java.util.Scanner;

public class TerminalInput {
    public static void main(String[] args) {
        // System.in represents the standard input
        // System.out represents the standard output
        // System.err represents the standard error
        // Scanner scans the standard input for types
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert an int: ");
        scanner.nextInt();
        System.out.print("Insert a long: ");
        scanner.nextLong();
        System.out.print("Insert a float: ");
        scanner.nextFloat();
        System.out.print("Insert a double: ");
        scanner.nextDouble();
        System.out.print("Insert a String: ");
        scanner.next();
        System.out.print("Insert a String beginning with s: ");
        scanner.next("[a-zA-Z]+");
        scanner.close();
    }
}
