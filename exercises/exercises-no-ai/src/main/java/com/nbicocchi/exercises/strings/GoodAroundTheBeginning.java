package com.nbicocchi.exercises.strings;

public class GoodAroundTheBeginning {
    public static boolean goodAroundTheBeginning(String s) {
        return s.startsWith("good") || s.startsWith("good", 1);
        // alternative version
        // String a = s.substring(0, 4);
        // String b = s.substring(1, 5);
        // return a.equals("good") || b.equals("good");
    }
}
