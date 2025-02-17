package com.nbicocchi.tutorials.functional;

import java.util.ArrayList;
import java.util.List;

public class FunctionalInterfaces03 {
    public static void main(String[] args) {
        List<Student> students = StreamCreation.getStudentsAsList();
        List<Student> result;
        result = filterStudents(students, s -> s.getAverage() >= 26 && s.getAverage() <= 30, s -> String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()), s -> System.out.println(s));
        System.out.println("filterStudents()...");
        System.out.println(result);
    }

    public static List<Student> filterStudents(List<Student> students, StudentPredicate sp, StudentFunction sf, StudentConsumer sc) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (sp.test(s)) {
                String str = sf.apply(s);
                sc.accept(str);
                result.add(s);
            }
        }
        return result;
    }

    public interface StudentPredicate {
        boolean test(Student s);
    }

    public interface StudentFunction {
        String apply(Student s);
    }

    public interface StudentConsumer {
        void accept(String s);
    }
}
