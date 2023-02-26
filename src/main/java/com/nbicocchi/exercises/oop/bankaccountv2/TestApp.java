package com.nbicocchi.exercises.oop.bankaccountv2;

/**
 * We want to model two different bank accounts. They allow deposits, withdrawals, transfers to other accounts and
 * annual interests.
 * BankAccountIntesa has no fees for all the operations, BUT applies no interests, allows transfers within the same
 * country only, and does not allow negative balance.
 * BankAccountUnicredit applies interests, allows transfers to all nations, allows negative balance BUT applies fees
 * for all operations. (1pc for withdrawals, 1pc for deposits, 2pc annual interest)
 * IBAN must have this format: "IT472121100900000002356987411"
 * Country is represented by the first two chars of the IBAN
 * Implement the classes described in UML inside the png file contained in this package.
 * Test them with the following main.
 *
 * @author Nicola Bicocchi
 */
public class TestApp {
    public static void main(String[] args) {
        BankAccount a1 = new BankAccountIntesa("IT472121100900000002356987411", 10000);
        BankAccount a2 = new BankAccountUnicredit("DE435345300900000002342422345", 10000);
        System.out.println(a1);
        System.out.println("* Deposit 1000");
        a1.deposit(1000.0);
        System.out.println(a1);
        System.out.println("* Withdraw 1000");
        a1.withdraw(1000);
        System.out.println(a1);
        System.out.println("* Transfer 1000");
        a1.transfer(a2, 1000);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println("* Add interest");
        a1.addAnnualInterest();
        System.out.println(a1);
        System.out.println("* Add interest");
        a2.addAnnualInterest();
        System.out.println(a2);
        System.out.println("* Withdraw 15000");
        a1.withdraw(15000);
        System.out.println(a1);
        System.out.println("* Withdraw 15000");
        a2.withdraw(15000);
        System.out.println(a2);
    }
}