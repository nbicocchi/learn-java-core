package com.nbicocchi.exercises.oop.bankaccountv1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountIntesaTest {
    @Test
    void getIBAN() {
        BankAccountIntesa a = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        assertEquals("IT472121100900000002356987411", a.getIBAN());
    }

    @Test
    void setIBAN() {
        BankAccountIntesa a = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        a.setIBAN("IT472121100900000002356987412");
        assertEquals("IT472121100900000002356987412", a.getIBAN());
    }

    @Test
    void getBalance() {
        BankAccountIntesa a = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        assertEquals(10000, a.getBalance());
    }

    @Test
    void setBalance() {
        BankAccountIntesa a = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        a.setBalance(12000);
        assertEquals(12000, a.getBalance());
    }

    @Test
    void getCountry() {
        BankAccountIntesa a = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        assertEquals("IT", a.getCountry());
    }

    @Test
    void deposit() {
        BankAccountIntesa a = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        a.deposit(1000);
        assertEquals(11000, a.getBalance());
    }

    @Test
    void withdraw() {
        BankAccountIntesa a = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        a.withdraw(1000);
        assertEquals(9000, a.getBalance());
        a.withdraw(10000);
        assertEquals(0, a.getBalance());
    }

    @Test
    void transfer() {
        BankAccountIntesa a1 = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        BankAccountIntesa a2 = new BankAccountIntesa("IT472121100900000002356987412", 10000);
        BankAccountIntesa a3 = new BankAccountIntesa("DE472121100900000007878654355", 10000);
        a1.transfer(a2, 1000);
        assertEquals(9000, a1.getBalance());
        assertEquals(11000, a2.getBalance());
        a1.transfer(a2, 10000);
        assertEquals(0, a1.getBalance());
        assertEquals(20000, a2.getBalance());
        a2.transfer(a3, 10000);
        assertEquals(20000, a2.getBalance());
        assertEquals(10000, a3.getBalance());
    }

    @Test
    void addAnnualInterest() {
        BankAccountIntesa a = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        a.addAnnualInterest();
        assertEquals(10000, a.getBalance());
    }
}