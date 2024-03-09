package com.nbicocchi.tutorials.collections;

import java.util.*;
import java.util.random.RandomGenerator;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class SpeedTest {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> treeSet = new TreeSet<>();
        Map<Integer, Integer> hashMap = new HashMap<>();
        int items = 40000;

        System.out.printf("FILL ARRAYLIST [time=%d ms]\n", NANOSECONDS.toMillis(fillList(arrayList, items)));
        System.out.printf("FILL LINKEDLIST [time=%d ms]\n", NANOSECONDS.toMillis(fillList(linkedList, items)));
        System.out.printf("FILL HASHSET [time=%d ms]\n", NANOSECONDS.toMillis(fillCollection(hashSet, items)));
        System.out.printf("FILL TREESET [time=%d ms]\n", NANOSECONDS.toMillis(fillCollection(treeSet, items)));
        System.out.printf("FILL HASHMAP [time=%d ms]\n", NANOSECONDS.toMillis(fillMap(hashMap, items)));
        System.out.printf("RETR ARRAYLIST [time=%d ms]\n", NANOSECONDS.toMillis(retrieveCollection(arrayList, items)));
        System.out.printf("RETR LINKEDLIST [time=%d ms]\n", NANOSECONDS.toMillis(retrieveCollection(linkedList, items)));
        System.out.printf("RETR HASHSET [time=%d ms]\n", NANOSECONDS.toMillis(retrieveCollection(hashSet, items)));
        System.out.printf("RETR TREESET [time=%d ms]\n", NANOSECONDS.toMillis(retrieveCollection(treeSet, items)));
        System.out.printf("RETR HASHMAP [time=%d ms]\n", NANOSECONDS.toMillis(retrieveMap(hashMap, items)));
    }

    public static long fillList(List<Integer> list, int items) {
        RandomGenerator rnd = RandomGenerator.getDefault();
        long start = System.nanoTime();
        for (int i = 0; i < items; i++) {
            list.add(rnd.nextInt(items));
        }
        return System.nanoTime() - start;
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
