package com.nbicocchi.exercises.collections;

import java.util.Iterator;
import java.util.List;

public class DownSize {
    public static void downsize(List<String> list, int n) {
        int index = 1;
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            iterator.next();
            if (index++ % n == 0) {
                iterator.remove();
            }
        }
    }
}
