package com.nbicocchi.tutorials.functional;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Comparable<Student>, Serializable {
    String name;
    String lastname;
    String phone;
    double average;

    public Student(String name, String lastname, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.average = 0.0;
    }

    public Student(String name, String lastname, double average) {
        this.name = name;
        this.lastname = lastname;
        this.phone = "";
        this.average = average;
    }

    public Student(String name, String lastname, String phone, double average) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public int compareTo(Student s) {
        // Compare lastname, and then name
        int compare = lastname.compareTo(s.lastname);
        if (compare == 0) {
            compare = name.compareTo(s.name);
        }
        return compare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, phone, average);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return Double.compare(student.average, average) == 0 && Objects.equals(name, student.name) && Objects.equals(lastname, student.lastname) && Objects.equals(phone, student.phone);
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", lastname='" + lastname + '\'' + ", phone='" + phone + '\'' + ", average=" + average + '}';
    }
}
