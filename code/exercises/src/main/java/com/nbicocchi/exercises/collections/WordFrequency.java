package com.nbicocchi.exercises.collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequency {

    public static Map<String, Integer> wordFrequency(String filename) throws IOException {
        Map<String, Integer> frequencies = new HashMap<>();
        for (String line : getLines(filename)) {
            for (String word : lineToWords(line)) {
                words.merge(word, 1, Integer::sum);
            }
        }
        return frequencies;
    }

    public static List<String> getLines(String filename) throws IOException {
        return Files.readAllLines(Path.of(filename));
    }

    public static List<String> lineToWords(String line) {
        return Arrays.asList(line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
    }
}
