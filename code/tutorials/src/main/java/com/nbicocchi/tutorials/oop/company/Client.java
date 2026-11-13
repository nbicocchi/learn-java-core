package com.nbicocchi.tutorials.oop.company;

import java.time.LocalDate;

public class Client extends Person {
    protected String contractNumber;
    protected boolean gold;

    public Client(String name, LocalDate birthDay, String address, String contractNumber, boolean gold) {
        super(name, birthDay, address);
        this.contractNumber = contractNumber;
        this.gold = gold;
    }

    // getters and setters here
}
