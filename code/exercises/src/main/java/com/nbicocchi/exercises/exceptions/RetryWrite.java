package com.nbicocchi.exercises.exceptions;

import java.io.IOException;
import java.io.UncheckedIOException;

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

    public static void write(String message) throws IOException {
        throw new IOException("Unable to send: " + message);
    }
}
