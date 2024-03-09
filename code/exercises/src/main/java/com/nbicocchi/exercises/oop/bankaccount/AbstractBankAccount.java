package com.nbicocchi.exercises.oop.bankaccount;

/**
 * Abstract implementation of the BankAccount interface
 */
public abstract class AbstractBankAccount implements BankAccount {
    /**
     * The IBAN of the account
     */
    protected String IBAN;

    /**
     * The balance of the account
     */
    protected double balance;

    /**
     * The fee to be applied to deposits and withdrawals
     */
    protected double operationFee;

    /**
     * The interest rate to be applied
     */
    protected double interestRate;

    /**
     * Construct a new BankAccount
     * @param IBAN the IBAN of the account
     * @param balance the initial balance of the account
     * @param operationFee the fee to be applied to deposit and withdraw operations
     * @param interestRate the interest rate to be applied
     */
    public AbstractBankAccount(String IBAN, double balance, double operationFee, double interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
        setIBAN(IBAN);
        setOperationFee(operationFee);
    }

    @Override
    public String getIBAN() {
        return IBAN;
    }

    /**
     * Sets the IBAN. Only compliant formats are allowed.
     * An IBAN is valid if its length is comprised between [8, 34] and its first two chars are uppercase letters.
     * @param IBAN the IBAN to be set.
     */
    @Override
    public void setIBAN(String IBAN) {
        checkIBAN(IBAN);
        this.IBAN = IBAN;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public double getOperationFee() {
        return operationFee;
    }

    /**
     * Sets operation fees. Only positive values are allowed.
     * @param operationFee the operation fee to be set
     */
    @Override
    public void setOperationFee(double operationFee) {
        if (operationFee < 0.0) {
            throw new IllegalArgumentException("Invalid negative fee");
        }
        this.operationFee = operationFee;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Deposit an amount on the account. No fees are applied.
     * @param amount the amount to be deposited
     */
    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraw an amount from the account. No fees are applied.
     * @param amount the amount to be withdrawn
     * @return the amount withdrawn
     */
    @Override
    public double withdraw(double amount) {
        balance -= amount;
        return amount;
    }

    /**
     * Transfers and amount on a different account.
     * Fees are applied according to the specific rules of each account.
     * @param other the other bank account
     * @param amount the amount to be transferred
     * @return the amount transferred
     */
    @Override
    public double transfer(BankAccount other, double amount) {
        withdraw(amount);
        other.deposit(amount);
        return amount;
    }

    /**
     * Applies interests to the account
     */
    @Override
    public void addInterest() {
        balance += balance * interestRate;
    }

    /**
     * Applies a fee to the account
     */
    protected void applyFee() {
        balance -= operationFee;
    }

    /**
     * Verifies the integrity of an IBAN
     * @param IBAN the IBAN to be verified
     */
    protected void checkIBAN(String IBAN) {
        if (IBAN.length() < 8 || IBAN.length() > 34) {
            throw new IllegalArgumentException("Invalid length");
        }
        String countryCode = IBAN.substring(0, 2);
        if (!(Character.isUpperCase(countryCode.charAt(0)) && Character.isUpperCase(countryCode.charAt(1)))) {
            throw new IllegalArgumentException("Invalid country code");
        }
    }
}
