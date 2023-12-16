package com.nbicocchi.exercises.functional;

import java.util.List;
import java.util.Map;

public class JustPrint {
    public static <T> void justPrintList(List<T> strings) {
        strings.forEach(s -> System.out.println("Hello " + s + "!"));
    }

    public static <K,V> void justPrintMap(Map<K, V> map) {
        map.forEach((k, v) -> System.out.println("k:" + k + ", v:" + v));
    }
}
