package com.nbicocchi.exercises.strings;

import java.util.StringTokenizer;

public class CountYZ {
    public static int countYZ(String string) {
        int countYZ = 0;
        StringTokenizer tokenizer = new StringTokenizer(string);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().toLowerCase();
            char lastChar = token.charAt(token.length() - 1);
            if (lastChar == 'y' || lastChar == 'z') {
                countYZ++;
            }
        }
        return countYZ;
    }
}
