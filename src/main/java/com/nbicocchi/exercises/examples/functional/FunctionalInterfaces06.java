package com.nbicocchi.exercises.examples.functional;

import com.nbicocchi.exercises.examples.Student;
import com.nbicocchi.exercises.examples.StreamCreation;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalInterfaces06 {
    public static void main(String[] args) {
        List<Student> students = StreamCreation.getStudentsAsList();
        students.stream()
                .filter(s -> s.getAverage() >= 26 && s.getAverage() <= 30)
                .peek(s -> String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()))
                .collect(Collectors.toList());
    }
}
