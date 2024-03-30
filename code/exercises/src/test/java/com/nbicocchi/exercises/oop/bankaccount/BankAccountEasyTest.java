package com.nbicocchi.exercises.oop.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountEasyTest {
    BankAccount bankAccount;

    @BeforeEach
    void init() {
        bankAccount = new BankAccountEasy("IT0000012345", 1000);
    }

    @Test
    void checkIBAN() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccountEasy("IT00123", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new BankAccountEasy("It001234", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new BankAccountEasy("It001234", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new BankAccountEasy("it001234", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new BankAccountEasy("i0001234", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new BankAccountEasy("IT123456789012345678901234567890123", 0.0));
        assertDoesNotThrow(() -> new BankAccountEasy("IT001234", 0.0));
    }

    @Test
    void deposit() {
        bankAccount.deposit(1000);
        assertEquals(2000, bankAccount.getBalance());
    }

    @Test
    void withdraw() {
        assertEquals(1000, bankAccount.withdraw(2000));
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void transfer() {
        BankAccountEasy otherInternational = new BankAccountEasy("DE0000012345", 1000);
        BankAccountEasy otherNational = new BankAccountEasy("IT0000045678", 1000);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(otherInternational, 1000));
        assertEquals(1000, bankAccount.transfer(otherNational,2000));
        assertEquals(0, bankAccount.getBalance());
        assertEquals(2000, otherNational.getBalance());
    }

    @Test
    void addInterest() {
        bankAccount.addInterest();
        assertEquals(1000, bankAccount.getBalance());
    }
}