package com.nbicocchi.exercises.oop.bankaccount;

/**
 * Defines common features of bank accounts.
 */
public interface BankAccount {
    String getIBAN();

    void setIBAN(String IBAN);

    double getBalance();

    void setBalance(double balance);

    double getOperationFee();

    void setOperationFee(double operationFee);

    double getInterestRate();

    void setInterestRate(double interestRate);

    void deposit(double amount);

    double withdraw(double amount);

    double transfer(BankAccount other, double amount);

    void addInterest();

    void applyFee();

    /**
     * Verifies the integrity of an IBAN
     * @param IBAN the IBAN to be verified
     */
    static void checkIBAN(String IBAN) {
        if (IBAN.length() < 8 || IBAN.length() > 34) {
            throw new IllegalArgumentException("Invalid length");
        }
        String countryCode = IBAN.substring(0, 2);
        if (!(Character.isLetter(countryCode.charAt(0)) && Character.isLetter(countryCode.charAt(1)))) {
            throw new IllegalArgumentException("Invalid country code");
        }
        if (!(Character.isUpperCase(countryCode.charAt(0)) && Character.isUpperCase(countryCode.charAt(1)))) {
            throw new IllegalArgumentException("Invalid country code");
        }
    }
}
