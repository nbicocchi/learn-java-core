package com.nbicocchi.exercises.collections;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class SortAccount {
    public static class Account {
        double amount;
        double interestRate;
        LocalDateTime duePayment;

        public Account(double amount, double interestRate, LocalDateTime duePayment) {
            this.amount = amount;
            this.interestRate = interestRate;
            this.duePayment = duePayment;
        }

        public double getAmount() {
            return amount;
        }

        public double getInterestRate() {
            return interestRate;
        }

        public LocalDateTime getDuePayment() {
            return duePayment;
        }

        public void applyInterest() {
            amount += amount * interestRate;
        }
    }

    public static void sortByAmount(List<Account> accounts) {
        Collections.sort(accounts, (a1, a2) -> Double.valueOf(a1.getAmount()).compareTo(a2.getAmount()));
    }
    public static void sortByInterestRate(List<Account> accounts) {
        Collections.sort(accounts, (a1, a2) -> Double.valueOf(a1.getInterestRate()).compareTo(a2.getInterestRate()));
    }
    public static void sortByDuePayment(List<Account> accounts) {
        Collections.sort(accounts, (a1, a2) -> a1.getDuePayment().compareTo(a2.getDuePayment()));
    }
}
