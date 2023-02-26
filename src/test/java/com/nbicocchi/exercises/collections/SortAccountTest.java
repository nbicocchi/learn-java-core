package com.nbicocchi.exercises.collections;

import com.nbicocchi.exercises.functional.BankAccount;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortAccountTest {

    @Test
    void applyInterest() {
        List<SortAccount.Account> accounts = new ArrayList<>(List.of(
                new SortAccount.Account(1000, 0.03, LocalDateTime.of(2023, 01, 01, 0, 0)),
                new SortAccount.Account(2000, 0.02, LocalDateTime.of(2022, 01, 01, 0, 0)),
                new SortAccount.Account(3000, 0.01, LocalDateTime.of(2024, 01, 01, 0, 0))
        ));
        SortAccount.sortByAmount(accounts);
        assertEquals(1000, accounts.get(0).getAmount());
        SortAccount.sortByInterestRate(accounts);
        assertEquals(3000, accounts.get(0).getAmount());
        SortAccount.sortByDuePayment(accounts);
        assertEquals(2000, accounts.get(0).getAmount());
    }
}