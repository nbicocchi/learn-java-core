package com.nbicocchi.exercises.collections;

import java.util.*;

public class Reverse {
    public static List<String> reverse(List<String> sentence) {
        Deque<String> stack = new LinkedList<>();
        for (String word : sentence) {
            stack.addFisrt(word);
        }
        return List.of(String.valueOf(stack));
    }
}
