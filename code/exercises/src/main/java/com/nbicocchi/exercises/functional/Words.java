package com.nbicocchi.exercises.functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Words {

    public static List<String> lineToWords(String line) {
        return Arrays.asList(line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
    }

    public static List<String> getLines(String filename) throws IOException {
        return Files.readAllLines(Path.of(filename));
    }

    public static long howManyLines(String filename, String subString) throws IOException {
        List<String> book = getLines(filename);
        return book.stream().filter(line -> line.contains(subString)).count();
    }



    public static long howManyTimes(String filename, String word) throws IOException {
        List<String> book = getLines(filename);
        return book.stream().flatMap(line -> lineToWords(line).stream().filter(w -> w.compareTo(word) == 0)).count();
    }
}
