package com.nbicocchi.exercises.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapToPairs {
    public static <K, V> List<Pair<K, V>> mapToPairs(Map<K, V> src) {
        List<Pair<K, V>> dst = new ArrayList<>();
        for (K key : src.keySet()) {
            dst.add(new Pair<>(key, src.get(key)));
        }
        return dst;
    }
}
