package com.nbicocchi.tutorials.strings;

import java.time.Duration;

public class ConcatenateBenchmark {
    public static String concatenateSlow(int iterations) {
        // slow version
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s += 'a';
        }
        return s;
    }

    public static String concatenateFast(int iterations) {
        // fast version using StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append('a');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        concatenateSlow(10000);
        long end = System.nanoTime();
        System.out.println("Execution time: " + Duration.ofNanos(end - start).toMillis() + "ms");
    }
}
