package com.nbicocchi.exercises.examples;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class BankExample {

    static class BankAccount {
        private int balance;

        public BankAccount(int balance) {
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            balance -= amount;
        }

        public void deposit(int amount) {
            balance += amount;
        }
    }

    static class BankUser extends Thread {
        BankAccount account;
        RandomGenerator rnd;

        public BankUser(String name, BankAccount account) {
            super(name);
            this.account = account;
            this.rnd = RandomGenerator.getDefault();
        }

        public void run() {
            while (true) {
                synchronized (account) {
                    int amount = rnd.nextInt(100);
                    if (account.getBalance() < amount) {
                        System.out.printf("%s quitting [balance=%d, amount=%d]\n", Thread.currentThread().getName(), account.getBalance(), amount);
                        break;
                    }
                    System.out.printf("%s is going to withdraw [balance=%d, amount=%d]\n", Thread.currentThread().getName(), account.getBalance(), amount);
                    account.withdraw(amount);
                    System.out.printf("%s completed withdraw [balance=%d]\n", Thread.currentThread().getName(), account.getBalance());
                    if (account.getBalance() < 0) {
                        System.out.printf("%s DOH!! [balance=%d, amount=%d]\n", Thread.currentThread().getName(), account.getBalance(), amount);
                        break;
                    }
                    Thread.yield();
                }
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(150);
        BankUser homer = new BankUser("Homer", account);
        BankUser marge = new BankUser("Marge", account);
        homer.start();
        marge.start();
    }
}
