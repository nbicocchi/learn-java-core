package com.nbicocchi.exercises.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapToPairs {
    public static <K, V> List<Pair<K, V>> mapToPairs(Map<K, V> src) {
        List<Pair<K, V>> dst = new ArrayList<>();
        for (Map.Entry<K, V> entry : src.entrySet()) {
            dst.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        return dst;
    }
}
