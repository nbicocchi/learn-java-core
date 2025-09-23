package com.nbicocchi.exercises.strings;

public class RemoveFirstLast {
    public static String removeFirstLast(String s){
        if(s.length() < 2){
            return s;
        }
        if(s.charAt(0) == s.charAt(s.length() - 1)){
            return s.substring(1, s.length() - 1);
        }
        return s;
        // compact version
        // return (s.charAt(0) == s.charAt(s.length() - 1)) ? s.substring(1, s.length() - 1) : s;
    }
}
