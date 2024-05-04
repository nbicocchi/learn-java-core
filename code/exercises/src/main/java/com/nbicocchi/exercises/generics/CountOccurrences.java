package com.nbicocchi.exercises.generics;

import java.util.Objects;

public class CountOccurrences {
    public static <T> int countOccurrences(T[] src, T item){
        int occurrences = 0;
        for(T member : src){
            if(Objects.equals(member, item)){
                occurrences++;
            }
        }
        return occurrences;
    }
}
