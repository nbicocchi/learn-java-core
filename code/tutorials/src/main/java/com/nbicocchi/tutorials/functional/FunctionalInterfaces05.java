package com.nbicocchi.tutorials.functional;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalInterfaces05 {
    public static void main(String[] args) {
        List<Student> students = StreamCreation.getStudentsAsList();
        students.stream()
                .filter(s -> s.getAverage() >= 26 && s.getAverage() <= 30)
                .peek(s -> System.out.printf("%s_%s_%f%n", s.getLastname(), s.getName(), s.getAverage()))
                .collect(Collectors.toList());
    }
}
