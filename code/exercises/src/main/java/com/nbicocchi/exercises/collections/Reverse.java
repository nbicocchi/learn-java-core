package com.nbicocchi.exercises.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Reverse {
    public static List<String> reverse(List<String> sentence) {
        Deque<String> stack = new ArrayDeque<>();
        for (String s : sentence) {
            stack.push(s);
        }
        return new ArrayList<>(stack);
    }
}
