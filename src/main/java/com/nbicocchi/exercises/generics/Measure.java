package com.nbicocchi.exercises.generics;

public class Measure {
    static <T> T max(T[] values, Measurer<T> measurer) {
        T max = values[0];
        for (T item : values) {
            if (measurer.measure(item) > measurer.measure(max)) {
                max = item;
            }
        }
        return max;
    }

    static <T> T min(T[] values, Measurer<T> measurer) {
        T min = values[0];
        for (T item : values) {
            if (measurer.measure(item) < measurer.measure(min)) {
                min = item;
            }
        }
        return min;
    }

    public interface Measurer<T> {
        double measure(T item);
    }
}
