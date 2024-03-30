package com.nbicocchi.exercises.oop.bankaccount;

public class ConcreteBankAccount extends AbstractBankAccount{

    /**
     * Construct a new BankAccount
     *
     * @param IBAN         the IBAN of the account
     * @param balance      the initial balance of the account
     * @param operationFee the fee to be applied to deposit and withdraw operations
     * @param interestRate the interest rate to be applied
     */
    protected ConcreteBankAccount(String IBAN, double balance, double operationFee, double interestRate) {
        super(IBAN, balance, operationFee, interestRate);
    }
}
