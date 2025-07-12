package com.nbicocchi.exercises.generics;

public class MinMax {
    public static <T> T max(List<T> list, Comparator<? super T> cmp) {
        List<T> tmp = new ArrayList<>(list);
        tmp.sort(cmp);
        return tmp.get(list.size() - 1);
    }

    public static <T> T min(List<T> list, Comparator<? super T> cmp){
        List<T> tmp = new ArrayList<>(list);
        tmp.sort(cmp);
        return tmp.get(0);
    }
}

