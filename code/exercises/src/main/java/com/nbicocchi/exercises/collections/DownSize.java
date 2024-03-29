package com.nbicocchi.exercises.collections;

import java.util.Iterator;
import java.util.List;

public class DownSize {
    public static void downsize(List<String> list, int n) {
        for (int i = n; i <= list.size(); i += n) {
            list.remove(list.get(--i));
        }
    }
}
