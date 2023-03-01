package com.nbicocchi.exercises.collections;

import java.util.*;

public class Reverse {
    public static List<String> reverse(List<String> sentence) {
        List<String> dst = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        for (String word : sentence) {
            stack.addLast(word);
        }
        while (stack.size() > 0) {
            dst.add(stack.pollLast());
        }
        return dst;
    }
}
