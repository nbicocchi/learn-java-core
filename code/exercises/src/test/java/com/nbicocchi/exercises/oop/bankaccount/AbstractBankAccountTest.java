package com.nbicocchi.exercises.oop.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractBankAccountTest {
    ConcreteBankAccount bankAccount;
    @BeforeEach
    void init() {
        bankAccount = new ConcreteBankAccount("IT0000012345", 1000, 2.0, 0.0);
    }
    @Test
    void checkInterestRate() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.setOperationFee(-2.0));
    }
}
