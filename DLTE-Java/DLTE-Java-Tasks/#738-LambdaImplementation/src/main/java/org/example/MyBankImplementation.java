package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MyBankImplementation {

    public static void main(String[] args) {
        final List<Loan> loanList = new ArrayList<>();
        Loan[] loans = {
                new Loan(16752361523423L, 50000.0, new Date(2024, 01, 12), "Open", "John", 7656765456L),
                new Loan(87463253635221L, 100000.0, new Date(2023, 10, 9), "Open", "Peter", 7647342389L),
                new Loan(37463782339867L, 80000.0, new Date(2021, 05, 10), "Closed", "Lauren", 7687345243L),
                new Loan(65478366452377L, 70000.0, new Date(2024, 02, 20), "Open", "Han", 8974563726L),
                new Loan(674563872634L, 150000.0, new Date(2023, 7, 2), "Closed", "Alice", 9874563785L),
        };
        ArrayList<Loan> loansList = new ArrayList<>();
        for (Loan loan:loans) {
            loanList.add(loan);
        }
        final Scanner scanner = new Scanner(System.in);
        MyBank myBank = (data -> {
            System.out.println("Enter start date");
            int startDate = scanner.nextInt();
            System.out.println("Enter end date");
            int endDate = scanner.nextInt();
            for (Loan loan : loanList) {
                if (loan.getLoanDate().getDate()>=startDate && loan.getLoanDate().getDate()<=endDate) {
                    System.out.println(loan.getLoanDate()+" "+loan.getBorrowerName()+" "+loan.getLoanAmount());
                }
            }
        });

        myBank.filterByDate(loansList);
    }
}
