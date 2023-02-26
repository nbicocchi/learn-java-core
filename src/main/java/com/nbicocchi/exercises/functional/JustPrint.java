package com.nbicocchi.exercises.functional;

import java.util.List;

public class JustPrint {
    public static void justPrint(List<String> strings) {
        strings.stream().forEach(System.out::println);
    }
}
