package com.nbicocchi.exercises.examples.functional;

import com.nbicocchi.exercises.examples.Student;
import com.nbicocchi.exercises.examples.StreamCreation;

import java.util.ArrayList;
import java.util.List;

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

    public interface Predicate<T> {
        boolean test(T s);
    }

    public interface Function<T, R> {
        R apply(T s);
    }

    public interface Consumer<T> {
        void accept(T s);
    }
}
