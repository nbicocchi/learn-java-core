package com.nbicocchi.exercises.generics;

public class CountOccurrences {
    public static <T> int countOccurrences(T[] src, T item) {
        int count = 0;
        if (item == null) {
            for (T listItem : src) {
                if (listItem == null)
                    count++;
            }
        } else {
            for (T listItem : src) {
                if (item.equals(listItem))
                    count++;
            }
        }
        return count;

        // Compact version
//        int count = 0;
//        for (T t : src) {
//            if (item == null && t == null || t != null && t.equals(item)) {
//                count++;
//            }
//        }
//        return count;
    }
}
