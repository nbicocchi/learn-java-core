package com.nbicocchi.tutorials.functional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUse {
    public static void main(String[] args) {
        Stream<Student> s;

        /* Filtering */
        System.out.println("Filtering...");
        s = StreamCreation.getStreamFromList();
        s.filter(student -> student.getAverage() > 25).forEach(System.out::println);

        /* Mapping */
        System.out.println("Mapping...");
        s = StreamCreation.getStreamFromList();
        s.map(Student::getAverage).forEach(System.out::println);

        /* Mapping */
        System.out.println("Mapping...");
        s = StreamCreation.getStreamFromList();
        s.map(student -> {
            double avg = student.getAverage();
            return student.getName().equals("John") ? avg + 2 : avg;
        }).forEach(System.out::println);

        /* Aggregation (average) */
        System.out.println("Aggregation...");
        s = StreamCreation.getStreamFromList();
        System.out.println(s.mapToDouble(Student::getAverage).average());

        /* Aggregation (count) */
        System.out.println("Aggregation...");
        s = StreamCreation.getStreamFromList();
        System.out.println(s.mapToDouble(Student::getAverage).count());

        /* Collect */
        System.out.println("Collection...");
        s = StreamCreation.getStreamFromList();
        List<Student> l = s.filter(student -> student.getAverage() > 25).collect(Collectors.toList());
        System.out.println(l);

        /* Collect */
        System.out.println("Collection...");
        s = StreamCreation.getStreamFromList();
        List<String> l2 = s.filter(student -> student.getAverage() > 25).map(Student::getLastname).collect(Collectors.toList());
        System.out.println(l2);

        /* Collect */
        System.out.println("Collection...");
        s = StreamCreation.getStreamFromList();
        Map<String, Double> m1 = s.filter(student -> student.getAverage() > 18).collect(Collectors.toMap(Student::getLastname, Student::getAverage));
        System.out.println(m1);
    }
}
