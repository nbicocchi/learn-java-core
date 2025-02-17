package com.nbicocchi.tutorials.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamCreation {
    public static List<Student> getStudentsAsList() {
        return new ArrayList<>(List.of(
                new Student("John", "Doe", 24),
                new Student("Dimebag", "Darrell", 25),
                new Student("Eric", "Baret", 28),
                new Student("Tyler", "Durden", 20),
                new Student("Diana", "Krall", 27),
                new Student("Jack", "Napier", 22),
                new Student("Rosa", "Luxemburg", 30)));
    }

    // Stream.of() creates a Stream<T> from T instances
    public static Stream<Student> getStreamOf() {
        return Stream.of(
                new Student("John", "Doe", 24),
                new Student("Dimebag", "Darrell", 25),
                new Student("Eric", "Baret", 28),
                new Student("Tyler", "Durden", 20),
                new Student("Diana", "Krall", 27),
                new Student("Jack", "Napier", 22),
                new Student("Rosa", "Luxemburg", 30));
    }

    public static Stream<Student> getStreamFromList() {
        return getStudentsAsList().stream();
    }

    public static Stream<Student> getStreamFromArray() {
        Student[] students = getStudentsAsList().toArray(new Student[]{});
        return Arrays.stream(students);
    }

    // accept() return void
    public static Stream<Student> getStreamBuilderAccept() {
        Stream.Builder<Student> builder = Stream.builder();
        builder.accept(new Student("Dimebag", "Darrell", 27));
        builder.accept(new Student("Diana", "Krall", 23));
        builder.accept(new Student("Melody", "Gardot", 30));
        return builder.build();
    }

    // add() return another Builder
    public static Stream<Student> getStreamBuilderAdd() {
        Stream.Builder<Student> builder = Stream.builder();
        return builder
                .add(new Student("Dimebag", "Darrell", 27))
                .add(new Student("Diana", "Krall", 23))
                .add(new Student("Melody", "Gardot", 30))
                .build();
    }
}
