package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalance() {
        BankAccount ba = new BankAccount(1000);
        assertEquals(1000, ba.getBalance());
    }

    @Test
    void deposit() {
        BankAccount ba = new BankAccount(1000);
        ba.deposit(1000);
        assertEquals(2000, ba.getBalance());
    }

    @Test
    void withdraw() {
        BankAccount ba = new BankAccount(1000);
        ba.withdraw(1000);
        assertEquals(0, ba.getBalance());
    }
}