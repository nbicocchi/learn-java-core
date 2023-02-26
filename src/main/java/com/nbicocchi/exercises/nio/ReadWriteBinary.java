package com.nbicocchi.exercises.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadWriteBinary {
    public static byte[] readBinaryFile(String filename) throws IOException {
        Path src = Paths.get(filename);
        return Files.readAllBytes(src);
    }

    public static void writeBinaryFile(String filename, byte[] bytes) throws IOException {
        Path dst = Paths.get(filename);
        Files.write(dst, bytes);
    }
}
