package com.nbicocchi.exercises.oop.bankaccount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountProTest {

    @Test
    void deposit() {
        BankAccountPro ba = new BankAccountPro("IT0000012345", 1000);
        ba.deposit(1000);
        assertEquals(1999, ba.getBalance());
    }

    @Test
    void withdraw() {
        BankAccountPro ba = new BankAccountPro("IT0000012345", 1000);
        ba.withdraw(2000);
        assertEquals(-1001, ba.getBalance());
    }

    @Test
    void addInterest() {
        BankAccountPro ba = new BankAccountPro("IT0000012345", 1000);
        ba.addInterest();
        assertEquals(1020, ba.getBalance());
    }
}