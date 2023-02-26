package com.nbicocchi.exercises.examples;

import java.util.*;
import java.util.Map;
import java.util.random.RandomGenerator;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class SpeedTest {
    public static void main(String[] args) {
        Collection<Integer> arrayList = new ArrayList<>();
        Collection<Integer> linkedList = new LinkedList<>();
        Collection<Integer> hashSet = new HashSet<>();
        Map<Integer, Integer> hashMap = new HashMap<>();
        int items = 40000;

        System.out.printf("FILL ARRAYLIST [time=%d ms]\n", NANOSECONDS.toMillis(fillCollection(arrayList, items)));
        System.out.printf("FILL LINKEDLIST [time=%d ms]\n", NANOSECONDS.toMillis(fillCollection(linkedList, items)));
        System.out.printf("FILL HASHSET [time=%d ms]\n", NANOSECONDS.toMillis(fillCollection(hashSet, items)));
        System.out.printf("FILL HASHMAP [time=%d ms]\n", NANOSECONDS.toMillis(fillMap(hashMap, items)));
        System.out.printf("RETR ARRAYLIST [time=%d ms]\n", NANOSECONDS.toMillis(retrieveCollection(arrayList, items)));
        System.out.printf("RETR LINKEDLIST [time=%d ms]\n", NANOSECONDS.toMillis(retrieveCollection(linkedList, items)));
        System.out.printf("RETR HASHSET [time=%d ms]\n", NANOSECONDS.toMillis(retrieveCollection(hashSet, items)));
        System.out.printf("RETR HASHMAP [time=%d ms]\n", NANOSECONDS.toMillis(retrieveMap(hashMap, items)));
    }

    public static long fillCollection(Collection<Integer> collection, int items) {
        RandomGenerator rnd = RandomGenerator.getDefault();
        long start = System.nanoTime();
        for (int i = 0; i < items; i++) {
            collection.add(rnd.nextInt(items));
        }
        return System.nanoTime() - start;
    }

    public static long retrieveCollection(Collection<Integer> collection, int items) {
        RandomGenerator rnd = RandomGenerator.getDefault();
        long start = System.nanoTime();
        for (int i = 0; i < items; i++) {
            collection.contains(rnd.nextInt(items));
        }
        return System.nanoTime() - start;
    }

    public static long fillMap(Map<Integer, Integer> map, int items) {
        RandomGenerator rnd = RandomGenerator.getDefault();
        long start = System.nanoTime();
        for (int i = 0; i < items; i++) {
            int n = rnd.nextInt(items);
            map.put(n, n);
        }
        return System.nanoTime() - start;
    }

    public static long retrieveMap(Map<Integer, Integer> map, int items) {
        RandomGenerator rnd = RandomGenerator.getDefault();
        long start = System.nanoTime();
        for (int i = 0; i < items; i++) {
            map.containsKey(rnd.nextInt(items));
        }
        return System.nanoTime() - start;
    }
}
