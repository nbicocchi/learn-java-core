package com.nbicocchi.tutorials.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecution {
    public static void main(String[] args) throws IOException {
        Process process = new ProcessBuilder("/bin/ls", "-al", "/").start();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }
        System.out.println(process.exitValue());
    }
}