package com.nbicocchi.exercises.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileCreateDelete {
    public static void createFile(String src) throws IOException {
        Files.createFile(Paths.get(src));
    }

    public static void deleteFile(String src) throws IOException {
        Files.deleteIfExists(Paths.get(src));
    }
}
