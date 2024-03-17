package com.nbicocchi.exercises.arrays;

public class CanBalance {
    public static int sum(int[] v, int begin, int end) {
        int sum = 0;
        for (int i = begin; i < end; i++) {
            sum += v[i];
        }
        return sum;
    }

    public static boolean canBalance(int[] v) {
        if(v.length < 2)
            return false;
        for(int splitIndex = 1; splitIndex < v.length; splitIndex++){
            if(sum(v, 0, splitIndex) == sum(v, splitIndex, v.length))
                return true;
        }
        return false;
    }
}
