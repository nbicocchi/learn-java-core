package com.nbicocchi.exercises.exceptions;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Random;

public class RetryWrite {
    public static void main(String[] args) {
        try {
            writeWithTries(3);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeWithTries(int maxTries) {
        int count = 0;
        while(true) {
            try {
                write("Hello world!");
                break;
            } catch (IOException e) {
                if (++count == maxTries) {
                    throw new UncheckedIOException(e);
                }
            }
        }
    }

    /**
     * Simulates a write with 66% probability of failing
     * @param message the message to write
     * @throws IOException when write fails
     */
    public static void write(String message) throws IOException {
        Random rnd = new Random();
        if(rnd.nextDouble(1) > 0.33){
            throw new IOException("Unable to send: " + message);
        }
    }
}
