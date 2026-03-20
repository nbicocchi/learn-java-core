package com.nbicocchi.exercises.oop_inheritance.employees;

public class App {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Loris", 10000),
                new Developer("Marco", 20000, "java,python"),
                new Manager("Giorgio", 30000, 8)
        };

        // Polymorphism: getDetails() behaves differently depending on object type
        for (Employee e : employees) {
            System.out.println(e.getDetails());
        }
    }
}
