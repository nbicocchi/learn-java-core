package com.nbicocchi.exercises.strings;

import java.util.StringTokenizer;

public class CountYZ {
    public static int countYZ(String string) {
         StringTokenizer str = new StringTokenizer(string);

        if(string.toLowerCase().endsWith("y") || string.toLowerCase().endsWith("z")){
            return str.countTokens();
        }

        return 0;
    }
}
