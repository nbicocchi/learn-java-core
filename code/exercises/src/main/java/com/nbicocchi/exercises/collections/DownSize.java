package com.nbicocchi.exercises.collections;

import java.util.List;

public class DownSize {
    public static void downsize(List<String> list, int n){
        for(int i = list.size() - 1; i >= 0; i--){
            if((i + 1) % n == 0){
                list.remove(i);
            }
        }
    }

}
