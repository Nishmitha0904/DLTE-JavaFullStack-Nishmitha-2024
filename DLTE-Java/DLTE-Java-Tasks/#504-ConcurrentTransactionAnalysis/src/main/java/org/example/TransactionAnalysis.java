package org.example;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis implements Runnable, TransactionFunctionality {

    public static void main(String[] args) {
        Transaction[] transaction = {
                new Transaction(new Date(2024, 02, 20),1000.0, "John", "Bills"),
                new Transaction(new Date(2024, 03, 15),5500.0, "Hazel", "Friend"),
                new Transaction(new Date(2024, 01, 25),35000.0,"Karan","Emergency"),
                new Transaction(new Date(2024, 02, 04),15000.0,"Sara","Family"),
                new Transaction(new Date(2023, 12, 22),7000.0,"Sara","Emergency"),
        };

        String beneficiary, remark;
        int startDate, endDate;
        Scanner scanner = new Scanner(System.in);

        TransactionAnalysis analysis = new TransactionAnalysis();
        System.out.println("MENU\n1. Filter the transactions based on a date range \n 2. Find the least amount transferred \n 3. Find the maximum amount transferred \n 4. Find the number of transaction made to particular beneficiary \n 5. Filter the transactions based on remarks");
        System.out.println("What do you want to do? Choose");
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                System.out.println("Enter the start date");
                startDate = scanner.nextInt();
                System.out.println("Enter the end date");
                endDate = scanner.nextInt();
                analysis.filterByDate(transaction, startDate, endDate);
                break;
            case 2:
                analysis.leastAmount(transaction);
                break;
            case 3:
                analysis.maximumAmount(transaction);
                break;
            case 4:
                System.out.println("Enter a beneficiary to check the number of transactions made");
                beneficiary = scanner.next();
                analysis.numberOfTransaction(transaction, beneficiary);
                break;
            case 5:
                System.out.println("Enter the remarks to check the details");
                remark = scanner.next();
                analysis.remarksBasedList(transaction, remark);
                break;
        }

    }

    @Override
    public void filterByDate(Transaction[] transactions, int startDate, int endDate) {
        for (Transaction each : transactions) {
            if (each.getDateOfTransaction().getDate()>=startDate && each.getDateOfTransaction().getDate()<=endDate) {
                System.out.println(each.getTransactionTo()+" "+each.getDateOfTransaction()+" "+each.getAmountInTransaction());
            }
        }
    }

    @Override
    public void leastAmount(Transaction[] transactions) {
        Double leastAmount = Double.MAX_VALUE;

        for (Transaction each : transactions) {
            if (each.getAmountInTransaction() < leastAmount) {
                leastAmount = each.getAmountInTransaction();
            }
        }
        System.out.println("The least amount transferred is "+leastAmount);
    }

    @Override
    public void maximumAmount(Transaction[] transactions) {
            Double maxAmount = Double.MIN_VALUE;

            for (Transaction each : transactions) {
                if (each.getAmountInTransaction() > maxAmount) {
                    maxAmount = each.getAmountInTransaction();
                }
            }
            System.out.println("The maximum amount transferred is "+maxAmount);
    }

    @Override
    public void numberOfTransaction(Transaction[] transactions, String beneficiary) {
                int numberOfTransactions=0;
                for (Transaction each : transactions) {
                    if (each.getTransactionTo().equals(beneficiary)) {
                        numberOfTransactions += 1;
                    }
                }
                System.out.println("The total number of transactions made to "+beneficiary+" is "+numberOfTransactions);
        }


    @Override
    public void remarksBasedList(Transaction[] transactions, String remark){
            for (Transaction each : transactions) {
                if (each.getRemarks().equals(remark)) {
                    System.out.println(each.getTransactionTo()+"  "+each.getAmountInTransaction());
                }
            }
        }


    @Override
    public void run() {

    }
}
