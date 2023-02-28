package com.nbicocchi.exercises.oop.bankaccount;

public class BankAccountUnicredit extends AbstractBankAccount {
    static final double FeeDeposit = 0.01;
    static final double FeeWithdraw = 0.01;
    static final double AnnualInterest = 0.02;

    public BankAccountUnicredit(String IBAN, double balance) {
        super(IBAN, balance);
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

    @Override
    public String toString() {
        return "BankAccountUnicredit{" + "IBAN='" + IBAN + '\'' + ", balance=" + balance + '}';
    }
}
