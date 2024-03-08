package org.example;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionAnalysis implements Runnable, TransactionFunctionality {

    Lock lock = new ReentrantLock();
    private static Transaction[] transaction = {
            new Transaction(new Date(2024, 02, 20), 1000.0, "John", "Bills"),
            new Transaction(new Date(2024, 03, 15), 5500.0, "Hazel", "Friend"),
            new Transaction(new Date(2024, 01, 25), 35000.0, "Karan", "Emergency"),
            new Transaction(new Date(2024, 02, 04), 15000.0, "Sara", "Family"),
            new Transaction(new Date(2023, 12, 22), 7000.0, "Sara", "Emergency"),
    };

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        TransactionAnalysis analysis = new TransactionAnalysis();
        int choice;
        while (true) {
            System.out.println("MENU\n1. Filter the transactions based on a date range \n2. Find the least amount transferred \n3. Find the maximum amount transferred \n4. Find the number of transaction made to particular beneficiary \n5. Filter the transactions based on remarks\n6. Exit");
            System.out.println("What do you want to do? Choose");
            choice = scanner.nextInt();
            lock.lock();
            switch (choice) {
                case 1:
                    analysis.filterByDate();
                    break;
                case 2:
                    analysis.leastAmount();
                    break;
                case 3:
                    analysis.maximumAmount();
                    break;
                case 4:
                    analysis.numberOfTransaction();
                    break;
                case 5:
                    analysis.remarksBasedList();
                    break;
                default:
                    System.exit(0);
            }
            lock.unlock();
        }
    }

    @Override
    public void filterByDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the start date");
        int startDate = scanner.nextInt();
        System.out.println("Enter the end date");
        int endDate = scanner.nextInt();
        for (Transaction each : transaction) {
            if (each.getDateOfTransaction().getDate() >= startDate && each.getDateOfTransaction().getDate() <= endDate) {
                System.out.println(each.getTransactionTo() + " " + each.getDateOfTransaction() + " " + each.getAmountInTransaction());
            }
        }
    }

    @Override
    public void leastAmount() {
        Double leastAmount = Double.MAX_VALUE;

        for (Transaction each : transaction) {
            if (each.getAmountInTransaction() < leastAmount) {
                leastAmount = each.getAmountInTransaction();
            }
        }
        System.out.println("The least amount transferred is " + leastAmount);
    }

    @Override
    public void maximumAmount() {
        Double maxAmount = Double.MIN_VALUE;

        for (Transaction each : transaction) {
            if (each.getAmountInTransaction() > maxAmount) {
                maxAmount = each.getAmountInTransaction();
            }
        }
        System.out.println("The maximum amount transferred is " + maxAmount);
    }

    @Override
    public void numberOfTransaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a beneficiary to check the number of transactions made");
        String beneficiary = scanner.next();
        int numberOfTransactions = 0;
        for (Transaction each : transaction) {
            if (each.getTransactionTo().equals(beneficiary)) {
                numberOfTransactions += 1;
            }
        }
        System.out.println("The total number of transactions made to " + beneficiary + " is " + numberOfTransactions);
    }


    @Override
    public void remarksBasedList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the remarks to check the details");
        String remark = scanner.next();
        for (Transaction each : transaction) {
            if (each.getRemarks().equals(remark)) {
                System.out.println(each.getTransactionTo() + "  " + each.getAmountInTransaction());
            }
        }
    }




}
