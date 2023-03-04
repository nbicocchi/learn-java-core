package com.nbicocchi.exercises.oop.bankaccount;

public class BankAccountEasy extends AbstractBankAccount {

    public BankAccountEasy(String IBAN, double balance) {
        super(IBAN, balance, 0.0, 0.0);
    }

    @Override
    public double withdraw(double amount) {
        double allowedAmount = Math.min(amount, balance);
        return super.withdraw(allowedAmount);
    }

    @Override
    public double transfer(BankAccount other, double amount) {
        String CountryCodeSrc = IBAN.substring(0, 2);
        String CountryCodeDst = other.getIBAN().substring(0, 2);
        if (!CountryCodeSrc.equals(CountryCodeDst)) {
            throw new IllegalArgumentException("International transfers invalid");
        }
        double allowedAmount = withdraw(amount);
        other.deposit(allowedAmount);
        return allowedAmount;
    }
}
