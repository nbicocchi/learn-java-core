package com.nbicocchi.exercises.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAttributes {

    public static boolean exists(String filename) {
        return Files.exists(Paths.get(filename));
    }

    public static boolean isReadable(String filename) {
        return Files.isReadable(Paths.get(filename));
    }

    public static boolean isWritable(String filename) {
        return Files.isWritable(Paths.get(filename));
    }

    public static boolean isExecutable(String filename) {
        return Files.isExecutable(Paths.get(filename));
    }

    public static boolean isRegularFile(String filename) {
        return Files.isRegularFile(Paths.get(filename));
    }

    public static boolean isDirectory(String filename) {
        return Files.isDirectory(Paths.get(filename));
    }

    public static long size(String filename) throws IOException {
        return Files.size(Paths.get(filename));
    }
}
