package com.nbicocchi.exercises.oop.basic;

/**
 * A bank account storing a balance and supporting deposit and withdraw operations.
 */
public class BankAccount {
    double balance;

    /**
     * Constructs and initializes a BankAccount with zero balance
     */
    public BankAccount() {
        balance = 0;
    }

    /**
     * Constructs and initializes a BankAccount with the specified balance
     */
    public BankAccount(double balance) {
        this.balance = balance;
    }

    /**
     * Return the current balance
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits the specified amount to the bank account
     * @param amount the amount to be deposited
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraw the specified amount from the bank account
     * @param amount the amount to be withdrawn
     */
    public void withdraw(double amount) {
        balance -= amount;
    }
}
