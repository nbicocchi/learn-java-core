package com.nbicocchi.exercises.strings;

public class Reverse {
    public static String reverse(String string){
        StringBuilder reversed = new StringBuilder(string);

        return reversed.reverse().toString();
    }
}
