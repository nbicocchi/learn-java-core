package com.nbicocchi.exercises.oop.bankaccountv2;

public class BankAccountUnicredit implements BankAccount {
    static final double FeeDeposit = 0.01;
    static final double FeeWithdraw = 0.01;
    static final double AnnualInterest = 0.02;
    String IBAN;
    double balance;

    public BankAccountUnicredit(String IBAN, double balance) {
        this.IBAN = IBAN;
        this.balance = balance;
    }

    @Override
    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    @Override
    public String getCountry() {
        return IBAN.substring(0, 2);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        balance -= amount * FeeDeposit;
    }

    @Override
    public double withdraw(double amount) {
        balance -= amount;
        balance -= amount * FeeWithdraw;
        return amount;
    }

    @Override
    public void transfer(BankAccount other, double amount) {
        withdraw(amount);
        other.deposit(amount);
    }

    @Override
    public void addAnnualInterest() {
        double amount = balance * AnnualInterest;
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccountUnicredit{" + "IBAN='" + IBAN + '\'' + ", balance=" + balance + '}';
    }
}
