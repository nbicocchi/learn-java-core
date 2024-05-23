package com.nbicocchi.exercises.arrays;

public class TenRun {
    public static int[] tenRun(int[] v){
        for (int i = 0; i < v.length-1; i++){
            if(v[i]%10==0  && v[i+1]%10!=0){
               v[i+1]=v[i];
            }
        }
        return v;
    }
}
