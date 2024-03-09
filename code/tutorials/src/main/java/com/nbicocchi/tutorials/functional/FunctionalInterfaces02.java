package com.nbicocchi.tutorials.functional;

import java.util.ArrayList;
import java.util.List;

public class FunctionalInterfaces02 {
    public static void main(String[] args) {
        List<Student> students = StreamCreation.getStudentsAsList();
        List<Student> result;
        StudentPredicate sp = new StudentPredicate() {

            @Override
            public boolean test(Student s) {
                return s.getAverage() >= 26 && s.getAverage() <= 30;
            }
        };
        StudentFunction sf = new StudentFunction() {

            @Override
            public String apply(Student s) {
                return String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage());
            }
        };
        StudentConsumer sc = new StudentConsumer() {

            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        result = filterStudents(students, sp, sf, sc);
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
