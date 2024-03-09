package com.nbicocchi.tutorials.basics;

public class TerminalOutput {
    public static void main(String[] args) {
        /* println() */
        System.out.println("1. Hello world!");

        /* print() (no carriage return) */
        System.out.print("2. Hello world!\n");

        /* printf() */
        System.out.printf("3. %s\n", "Hello world!");

        /* String.format() */
        String s = String.format("4. %s\n", "Hello world!");
        System.out.println(s);
    }
}
