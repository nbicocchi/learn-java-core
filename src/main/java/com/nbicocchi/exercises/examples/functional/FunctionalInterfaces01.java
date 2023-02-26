package com.nbicocchi.exercises.examples.functional;

import com.nbicocchi.exercises.examples.Student;
import com.nbicocchi.exercises.examples.StreamCreation;

import java.util.ArrayList;
import java.util.List;

public class FunctionalInterfaces01 {
    public static void main(String[] args) {
        List<Student> students = StreamCreation.getStudentsAsList();
        List<Student> result;
        result = filterStudentsByGrade(students, 24);
        System.out.println("filterStudentsByGrade()...");
        System.out.println(result);
        result = filterStudentsByGradeRange(students, 26, 30);
        System.out.println("filterStudentsByGradeRange()...");
        System.out.println(result);
    }

    public static List<Student> filterStudentsByGrade(List<Student> students, double average) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getAverage() == average) {
                System.out.println(String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()));
                result.add(s);
            }
        }
        return result;
    }

    public static List<Student> filterStudentsByGradeRange(List<Student> students, double low, double high) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getAverage() >= low && s.getAverage() <= high) {
                System.out.println(String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()));
                result.add(s);
            }
        }
        return result;
    }
}
