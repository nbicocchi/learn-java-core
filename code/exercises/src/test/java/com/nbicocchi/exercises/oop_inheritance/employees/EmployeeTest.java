package com.nbicocchi.exercises.oop_inheritance.employees;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {


    @Test
    void testEmployeePolymorphism() {
        // Create individual employee objects
        Manager manager = new Manager("Alice", 5000.0, 5);
        Developer developer = new Developer("Bob", 4000.0, "Java");
        Employee employee = new Employee("Charlie", 3000.0);

        // Check getDetails() for each object
        assertEquals(
                "Manager{name='Alice', salary=5000.0, teamSize=5}",
                manager.getDetails()
        );

        assertEquals(
                "Developer{name='Bob', salary=4000.0, language='Java'}",
                developer.getDetails()
        );

        assertEquals(
                "Employee{name='Charlie', salary=3000.0}",
                employee.getDetails()
        );
    }
}