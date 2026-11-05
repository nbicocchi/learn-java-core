package com.nbicocchi.exercises.exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadLineNoDelegation {
    public static String readLineNoDelegation(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
