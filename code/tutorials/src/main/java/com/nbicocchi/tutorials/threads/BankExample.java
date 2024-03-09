package com.nbicocchi.tutorials.threads;

import java.util.random.RandomGenerator;

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

        public BankUser(String name, BankAccount account) {
            super(name);
            this.account = account;
        }

        private void log(String action, int amount) {
            System.out.printf("[%s] [%14s] [balance=%d, amount=%d]\n",
                    Thread.currentThread().getName(),
                    action,
                    account.getBalance(),
                    amount);
        }

        public void run() {
            RandomGenerator rnd = RandomGenerator.getDefault();
            while (!interrupted()) {
                synchronized (account) {
                    if (account.getBalance() <= 0) {
                        log("ACCOUNT EMPTY", 0);
                        break;
                    }

                    int amount = rnd.nextInt(account.getBalance() + 1);

                    log("START WITHDRAW", amount);

                    account.withdraw(amount);

                    log("END WITHDRAW", amount);

                    if (account.getBalance() < 0) {
                        log("DOH!", amount);
                        break;
                    }
                    Thread.yield();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(2000);
        BankUser homer = new BankUser("HOMER", account);
        BankUser marge = new BankUser("MARGE", account);
        homer.start();
        marge.start();
        homer.join();
        marge.join();
    }
}
