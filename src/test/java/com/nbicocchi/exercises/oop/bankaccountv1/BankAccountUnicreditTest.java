package com.nbicocchi.exercises.oop.bankaccountv1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountUnicreditTest {
    @Test
    void getIBAN() {
        BankAccountUnicredit a = new BankAccountUnicredit("IT472121100900000002356987411", 10000);
        assertEquals("IT472121100900000002356987411", a.getIBAN());
    }

    @Test
    void setIBAN() {
        BankAccountUnicredit a = new BankAccountUnicredit("IT472121100900000002356987411", 10000);
        a.setIBAN("IT472121100900000002356987412");
        assertEquals("IT472121100900000002356987412", a.getIBAN());
    }

    @Test
    void getBalance() {
        BankAccountUnicredit a = new BankAccountUnicredit("IT472121100900000002356987411", 10000);
        assertEquals(10000, a.getBalance());
    }

    @Test
    void setBalance() {
        BankAccountUnicredit a = new BankAccountUnicredit("IT472121100900000002356987411", 10000);
        a.setBalance(12000);
        assertEquals(12000, a.getBalance());
    }

    @Test
    void getCountry() {
        BankAccountUnicredit a = new BankAccountUnicredit("IT472121100900000002356987411", 10000);
        assertEquals("IT", a.getCountry());
    }

    @Test
    void deposit() {
        BankAccountUnicredit a = new BankAccountUnicredit("IT472121100900000002356987411", 10000);
        a.deposit(1000);
        assertEquals(10990, a.getBalance());
    }

    @Test
    void withdraw() {
        BankAccountUnicredit a = new BankAccountUnicredit("IT472121100900000002356987411", 10000);
        a.withdraw(1000);
        assertEquals(8990, a.getBalance());
        a.withdraw(10000);
        assertEquals(-1110, a.getBalance());
    }

    @Test
    void transfer() {
        BankAccountUnicredit a1 = new BankAccountUnicredit("IT472121100900000002356987411", 10000);
        BankAccountUnicredit a2 = new BankAccountUnicredit("IT472121100900000002356987412", 10000);
        BankAccountUnicredit a3 = new BankAccountUnicredit("DE472121100900000007878654355", 10000);
        a1.transfer(a2, 1000);
        assertEquals(8990, a1.getBalance());
        assertEquals(10990, a2.getBalance());
        a1.transfer(a2, 10000);
        assertEquals(-1110, a1.getBalance());
        assertEquals(20890, a2.getBalance());
        a2.transfer(a3, 10000);
        assertEquals(10790, a2.getBalance());
        assertEquals(19900, a3.getBalance());
    }

    @Test
    void addAnnualInterest() {
        BankAccountUnicredit a = new BankAccountUnicredit("IT472121100900000002356987411", 10000);
        a.addAnnualInterest();
        assertEquals(10200, a.getBalance());
    }
}