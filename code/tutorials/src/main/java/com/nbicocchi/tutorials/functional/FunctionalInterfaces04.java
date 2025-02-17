package com.nbicocchi.tutorials.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaces04 {
    public static void main(String[] args) {
        List<Student> students = StreamCreation.getStudentsAsList();
        List<Student> result;
        result = filter(students, s -> s.getAverage() >= 26 && s.getAverage() <= 30, s -> String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()), s -> System.out.println(s));
        System.out.println("filterStudents()...");
        System.out.println(result);
    }

    public static <T, R> List<T> filter(List<T> l, Predicate<T> sp, Function<T, R> sf, Consumer<R> sc) {
        List<T> result = new ArrayList<>();
        for (T s : l) {
            if (sp.test(s)) {
                R x = sf.apply(s);
                sc.accept(x);
                result.add(s);
            }
        }
        return result;
    }
}
