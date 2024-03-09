package com.nbicocchi.tutorials.oop;

import java.time.LocalDate;

class Person {
    protected String name;
    protected int yearOfBirth;
    protected String address;

    public Person(String name, int yearOfBirth, String address) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.address = address;
    }

    // getters and setters here
}
class Client extends Person {
    protected String contractNumber;
    protected boolean gold;

    public Client(String name, int yearOfBirth, String address, String contractNumber, boolean gold) {
        super(name, yearOfBirth, address);
        this.contractNumber = contractNumber;
        this.gold = gold;
    }

    // getters and setters here
}

class Employee extends Person {
    protected LocalDate startDate;
    protected Long salary;

    public Employee(String name, int yearOfBirth, String address, LocalDate startDate, Long salary) {
        super(name, yearOfBirth, address);
        this.startDate = startDate;
        this.salary = salary;
    }

    // getters and setters here
}

class Manager extends Employee {
    protected boolean smile;

    public Manager(String name, int yearOfBirth, String address, LocalDate startDate, Long salary, boolean smile) {
        super(name, yearOfBirth, address, startDate, salary);
        this.smile = smile;
    }

    // getters and setters here

}

class Programmer extends Employee {
    protected String[] programmingLanguages;

    public Programmer(String name, int yearOfBirth, String address, LocalDate startDate, Long salary, String[] programmingLanguages) {
        super(name, yearOfBirth, address, startDate, salary);
        this.programmingLanguages = programmingLanguages;
    }

    // getters and setters here
}


public class CompanyApp {
    public static void main(String[] args) {
        Programmer john = new Programmer(
                "John Elephant",
                1985,
                "Some street 15",
                LocalDate.of(2023, 15, 06),
                500_000L,
                new String[] { "Java", "Scala", "Kotlin" });
    }
}


