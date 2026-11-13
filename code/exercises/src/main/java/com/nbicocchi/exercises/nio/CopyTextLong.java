package com.nbicocchi.exercises.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopyTextLong {
    public static void copyTextLong(String src, String dst) throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(src));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(dst))) {
            bufferedReader.lines().forEach(line -> {
                try {
                    bufferedWriter.write(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
