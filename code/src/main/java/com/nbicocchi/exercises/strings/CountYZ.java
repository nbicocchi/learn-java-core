package com.nbicocchi.exercises.strings;

import java.util.Scanner;

public class CountYZ {
    public static int countYZ(String string) {
        int countYZ = 0;
        Scanner scanner = new Scanner(string);
        while (scanner.hasNext()) {
            String token = scanner.next().toLowerCase();
            char lastChar = token.charAt(token.length() - 1);
            if (lastChar == 'y' || lastChar == 'z') {
                countYZ++;
            }
        }
        return countYZ;
    }
}
