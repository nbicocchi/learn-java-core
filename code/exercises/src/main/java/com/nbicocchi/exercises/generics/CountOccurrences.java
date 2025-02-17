package com.nbicocchi.exercises.generics;

import java.util.Objects;

public class CountOccurrences {
    public static <T> int countOccurrencesPlain(T[] src, T item) {
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
    }

    public static <T> int countOccurrencesObjects(T[] src, T item){
        int occurrences = 0;
        for(T member : src){
            if(Objects.equals(member, item)){
                occurrences++;
            }
        }
        return occurrences;
    }
}
