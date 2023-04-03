package com.nbicocchi.exercises.functional;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class JustPrintParametrized {
    public static <T> void justPrintList(List<T> strings, Consumer<? super T> consumer) {
        strings.forEach(consumer);
    }

    public static <K,V> void justPrintMap(Map<K, V> map, BiConsumer<? super K, ? super V> biConsumer) {
        map.forEach(biConsumer);
    }
}
