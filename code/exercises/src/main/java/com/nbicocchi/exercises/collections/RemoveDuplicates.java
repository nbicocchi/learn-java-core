package com.nbicocchi.exercises.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RemoveDuplicates {
    public static List<Integer> removeDuplicates(List<Integer> list) {
        if(list.isEmpty()){
            return list;
        }
        return new ArrayList<>(new HashSet<>(list));
    }
}
