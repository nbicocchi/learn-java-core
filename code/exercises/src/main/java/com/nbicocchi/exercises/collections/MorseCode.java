package com.nbicocchi.exercises.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MorseCode {

    public static String morseCode(String string) {
        if (string.isEmpty()) {
            return "";
        }
        if (!string.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("String must contains only alphabet letters");
        }

        Map<String, String> morse = Map.ofEntries(
            Map.entry("a", "._"),
            Map.entry("b", "_..."),
            Map.entry("c", "_._."),
            Map.entry("d", "_.."),
            Map.entry("e", "."),
            Map.entry("f", ".._."),
            Map.entry("g", "__."),
            Map.entry("h", "...."),
            Map.entry("i", ".."),
            Map.entry("j", ".___"),
            Map.entry("k", "_._"),
            Map.entry("l", "._.."),
            Map.entry("m", "__"),
            Map.entry("n", "_."),
            Map.entry("o", "___"),
            Map.entry("p", ".__."),
            Map.entry("q", "__._"),
            Map.entry("r", "._."),
            Map.entry("s", "..."),
            Map.entry("t", "_"),
            Map.entry("u", ".._"),
            Map.entry("v", "..._"),
            Map.entry("w", ".__"),
            Map.entry("x", "_.._"),
            Map.entry("y", "_.__"),
            Map.entry("z", "__..")
        );

        StringBuilder sb = new StringBuilder();
        for (char c : string.toLowerCase().toCharArray()) {
            sb.append(morse.get(String.valueOf(c))).append(" ");
        }

        // trim() eventually removes the last space
        return sb.toString().trim();
    }
}
