package com.nbicocchi.exercises.oop.bankaccount;

public class BankAccountPro extends AbstractBankAccount {
    public BankAccountPro(String IBAN, double balance, double operationFee, double interestRate) {
        super(IBAN, balance, 1, 0.02);
    }

    @Override
    public void deposit(double amount) {
        applyFee();
        super.deposit(amount);
    }

    @Override
    public double withdraw(double amount) {
        applyFee();
        return super.withdraw(amount);
    }
}
