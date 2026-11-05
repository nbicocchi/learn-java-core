package com.nbicocchi.exercises.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadWriteText {
    public static List<String> readTextFile(String filename) throws IOException {
        Path src = Paths.get(filename);
        return Files.readAllLines(src);
    }

    public static <T extends CharSequence> void writeTextFile(String filename, List<T> lines) throws IOException {
        Path dst = Paths.get(filename);
        Files.write(dst, lines);
    }
}
