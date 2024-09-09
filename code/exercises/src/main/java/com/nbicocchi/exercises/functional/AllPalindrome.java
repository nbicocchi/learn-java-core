package com.nbicocchi.exercises.functional;

import java.util.List;

public class AllPalindrome {
    public static boolean allPalindrome(List<String> strings){
        return strings.stream()
                .allMatch(x-> x.equals(new StringBuilder(x).reverse().toString()));
    }
}
