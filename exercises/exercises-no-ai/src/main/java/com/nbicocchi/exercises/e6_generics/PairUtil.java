package com.nbicocchi.exercises.e6_generics;

public class PairUtil {
    public static <K, V> Pair<V, K> swap(Pair<K, V> src) {
        return new Pair<>(src.getSecond(), src.getFirst());
    }
}
