package com.nbicocchi.exercises.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Reverse {
    public static List<String> reverse(List<String> sentence) {
        Deque<String> queue = new ArrayDeque<>();
        for (String s : sentence) {
            queue.addFirst(s);
        }
        return new ArrayList<>(queue);
    }
}
