package com.nbicocchi.exercises.collections;

import java.util.HashMap;

public class MorseCode {
    public static String morseCode(String string) {
        if (string == null || string.isEmpty()) {
            return "";
        }

        HashMap<Character, String> map = new HashMap<>();
        map.put('a', "._");
        map.put('b', "_...");
        map.put('c', "_._.");
        map.put('d', "_..");
        map.put('e', ".");
        map.put('f', ".._.");
        map.put('g', "__.");
        map.put('h', "....");
        map.put('i', "..");
        map.put('j', ".___");
        map.put('k', "_._");
        map.put('l', "._..");
        map.put('m', "__");
        map.put('n', "_.");
        map.put('o', "___");
        map.put('p', ".__.");
        map.put('q', "__._");
        map.put('r', "._.");
        map.put('s', "...");
        map.put('t', "_");
        map.put('u', ".._");
        map.put('v', "..._");
        map.put('w', ".__");
        map.put('x', "_.._");
        map.put('y', "_.__");
        map.put('z', "__..");

        StringBuilder builder = new StringBuilder();
        for (char c : string.toLowerCase().toCharArray()) {
            builder.append(map.get(c)).append(" ");
        }
        return builder.substring(0, builder.length() - 1);
    }
}
