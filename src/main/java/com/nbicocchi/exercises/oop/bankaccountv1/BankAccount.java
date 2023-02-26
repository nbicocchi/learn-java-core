package com.nbicocchi.exercises.oop.bankaccountv1;

public abstract class BankAccount {
    String IBAN;
    double balance;

    public BankAccount(String IBAN, double balance) {
        this.IBAN = IBAN;
        this.balance = balance;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCountry() {
        return IBAN.substring(0, 2);
    }

    public abstract void deposit(double amount);

    public abstract double withdraw(double amount);

    public abstract double transfer(BankAccount other, double amount);

    public abstract double addAnnualInterest();
}
