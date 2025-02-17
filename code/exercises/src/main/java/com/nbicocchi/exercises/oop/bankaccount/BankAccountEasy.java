package com.nbicocchi.exercises.oop.bankaccount;

/**
 * Simple bank account. Supports national transfers, pays no interest, do not apply any fee.
 */
public class BankAccountEasy extends AbstractBankAccount {

    /**
     * Constructs a new account with operation fees and interest rate set to 0.
     * @param IBAN the IBAN of the bank account
     * @param balance the initial balance of the bank account
     */
    public BankAccountEasy(String IBAN, double balance) {
        super(IBAN, balance, 0.0, 0.0);
    }

    /**
     * Withdraws from the account a specified amount.
     * Amount are limited to the available balance.
     * @param amount the amount to be withdrawn
     * @return the amount actually withdrawn
     */
    @Override
    public double withdraw(double amount) {
        double allowedAmount = Math.min(amount, balance);
        return super.withdraw(allowedAmount);
    }

    /**
     * Transfers and amount on a different account. Only same-country transfers are allowed.
     * @param other the other bank account
     * @param amount the amount to be transferred
     * @return the amount transferred
     */
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
