package com.nbicocchi.exercises.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecution {
    public static void main(String[] args) throws IOException {
        String line;
        Process p;

        /* p = Runtime.getRuntime().exec("/bin/ls -al /"); */
        p = (new ProcessBuilder("/bin/ls", "-al", "/")).start();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
        System.out.println(p.exitValue());
    }
}