package com.nbicocchi.exercises.strings;

import java.util.StringTokenizer;

public class CountYZ {
    public static int countYZ(String string) {
        int countYZ = 0;
        StringTokenizer tokenizer = new StringTokenizer(string);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().toLowerCase();
            if (token.endsWith("z") || token.endsWith("y")) {
                countYZ++;
            }
        }
        return countYZ;
    }
}
