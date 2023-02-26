package com.nbicocchi.exercises.oop.bankaccountv2;

public class BankAccountIntesa implements BankAccount {
    static final double FeeDeposit = 0.0;
    static final double FeeWithdraw = 0.0;
    static final double AnnualInterest = 0.0;
    String IBAN;
    double balance;

    public BankAccountIntesa(String IBAN, double balance) {
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
        double actualAmount = Math.min(balance, amount);
        balance -= actualAmount;
        balance -= actualAmount * FeeWithdraw;
        return actualAmount;
    }

    @Override
    public void transfer(BankAccount other, double amount) {
        if (!getCountry().equals(other.getCountry())) {
            return;
        }
        double actualAmount = withdraw(amount);
        other.deposit(actualAmount);
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
        return "BankAccountIntesa{" + "IBAN='" + IBAN + '\'' + ", balance=" + balance + '}';
    }
}
