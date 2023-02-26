package com.nbicocchi.exercises.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Reverse {
    public static List<String> reverse(List<String> sentence) {
        List<String> dst = new ArrayList<>();
        LinkedList<String> stack = new LinkedList<>();
        for (String word : sentence) {
            stack.push(word);
        }
        while (stack.size() > 0) {
            dst.add(stack.pop());
        }
        return dst;
    }
}
