package com.nbicocchi.tutorials.functional;

import java.util.function.*;

public class LambdaBasics {
    public static void main(String[] args) {
        Function<String, Integer> f1 = s -> s.length();
        BiFunction<String, String, Integer> f2 = (s1, s2) -> s1.length() + s2.length();

        Consumer<String> f3 = s -> System.out.println(s);
        BiConsumer<String, String> f4 = (s1, s2) -> System.out.println(s1 + " " + s2);

        Predicate<String> f5 = s -> s.length() < 6;
        BiPredicate<String, String> f6 = (s1, s2) -> s1.length() + s2.length() < 6;
    }
}
