package com.nbicocchi.exercises.collections;

import java.util.*;

public class Reverse {
    public static List<String> reverse(List<String> sentence) {
        Deque<String> stack = new ArrayDeque<>(sentence);
        List<String> dst = new ArrayList<>();
        while (!stack.isEmpty()) {
            dst.add(stack.removeLast());
        }
        return dst;
    }
}
