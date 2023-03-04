package com.nbicocchi.exercises.oop.bankaccount;

/**
 * Fully fledged bank account. Supports international transfers, pays 2pc interest, applies a fee of 1 euro on deposits
 * and withdrawals.
 */
public class BankAccountPro extends AbstractBankAccount {
    /**
     * Constructs a new bank account
     * @param IBAN the IBAN of the bank account
     * @param balance the initial balance of the bank account
     * @param operationFee the fee to be applied to deposit and withdraw operations
     * @param interestRate the interest rate to be applied
     */
    public BankAccountPro(String IBAN, double balance, double operationFee, double interestRate) {
        super(IBAN, balance, 1, 0.02);
    }

    /**
     * Deposit an amount on the account. Fees are applied.
     * @param amount the amount to be deposited
     */
    @Override
    public void deposit(double amount) {
        applyFee();
        super.deposit(amount);
    }

    /**
     * Withdraw an amount from the account. Fees are applied.
     * @param amount the amount to be withdrawn
     * @return the amount withdrawn
     */
    @Override
    public double withdraw(double amount) {
        applyFee();
        return super.withdraw(amount);
    }
}
