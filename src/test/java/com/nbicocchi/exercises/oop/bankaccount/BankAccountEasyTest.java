package com.nbicocchi.exercises.oop.bankaccount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountEasyTest {

    @Test
    void withdraw() {
        BankAccountEasy ba = new BankAccountEasy("IT0000012345", 1000);
        assertEquals(1000, ba.withdraw(2000));
        assertEquals(0, ba.getBalance());
    }

    @Test
    void transfer() {
        BankAccountEasy ba1 = new BankAccountEasy("IT0000012345", 1000);
        BankAccountEasy ba2 = new BankAccountEasy("DE0000012345", 1000);
        BankAccountEasy ba3 = new BankAccountEasy("IT0000012345", 1000);
        assertThrows(IllegalArgumentException.class, () -> ba1.transfer(ba2, 1000));
        assertEquals(1000, ba1.transfer(ba3,2000));
        assertEquals(0, ba1.getBalance());
        assertEquals(2000, ba3.getBalance());
    }
}