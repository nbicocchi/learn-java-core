package com.nbicocchi.exercises.collections;

import java.util.*;

public class WordFrequencySorted {

    public static Map<String, Integer> sortAndCut(Map<String, Integer> map, Comparator<Map.Entry<String, Integer>> comparator, int limit) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(comparator);
        entries = new ArrayList<>(entries.subList(0, limit));

        Map<String, Integer> dst = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            dst.put(entry.getKey(), entry.getValue());
        }
        return dst;
    }

    public static Map<String, Integer> mostFrequent(Map<String, Integer> map, int limit) {
        return sortAndCut(map, (e1, e2) -> e2.getValue() - e1.getValue(), limit);
    }

    public static Map<String, Integer> lessFrequent(Map<String, Integer> map, int limit) {
        return sortAndCut(map, (e1, e2) -> e1.getValue() - e2.getValue(), limit);
    }
}
