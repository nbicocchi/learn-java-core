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

    public static void writeWithTries(int maxTries)  {
        try {
            for (int attempt = 0; attempt < maxTries; attempt++) {
                write("Hello world!");
            }
        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    public static void write(String message) throws IOException {
        throw new IOException("Unable to send: " + message);
    }
}
