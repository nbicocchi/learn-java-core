package com.nbicocchi.exercises.oop.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalance() {
        BankAccount bankAccount = new BankAccount(1000);
        assertEquals(1000, bankAccount.getBalance());
        BankAccount emptyBankAccount = new BankAccount();
        assertEquals(0, emptyBankAccount.getBalance());
    }

    @Test
    void deposit() {
        BankAccount bankAccount = new BankAccount(1000);
        bankAccount.deposit(1000);
        assertEquals(2000, bankAccount.getBalance());
    }

    @Test
    void withdraw() {
        BankAccount bankAccount = new BankAccount(1000);
        bankAccount.withdraw(1000);
        assertEquals(0, bankAccount.getBalance());
    }
}