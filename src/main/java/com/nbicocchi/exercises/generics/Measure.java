package com.nbicocchi.exercises.generics;

public class Measure {
    public static <T> T max(T[] values, Measurer<T> measurer) {
        T max = values[0];
        for (T item : values) {
            if (measurer.measure(item) > measurer.measure(max)) {
                max = item;
            }
        }
        return max;
    }

    public static <T> T min(T[] array, Measurer<T> measurer) {
        T min = array[0];
        for (T item : array) {
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
