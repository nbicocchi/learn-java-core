package com.nbicocchi.exercises.warmup;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MonkeyTrouble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Is monkey (a) smiling? (true/false) ");
            boolean aSmile = scanner.nextBoolean();
            System.out.print("Is monkey (b) smiling? (true/false) ");
            boolean bSmile = scanner.nextBoolean();
            System.out.println(aSmile == bSmile ? "We are in trouble!" : "We are not in trouble!");
        } catch (InputMismatchException ex) {
            System.err.println("Non riesco a capire l'Input");
        } catch (NoSuchElementException ex) {
            System.err.println("Nessun Input fornito");
        } catch (IllegalStateException ex) {
            System.err.println("Scanner down");
        } finally {
            System.out.println("MonkeyTrouble done");
        }
    }
}
