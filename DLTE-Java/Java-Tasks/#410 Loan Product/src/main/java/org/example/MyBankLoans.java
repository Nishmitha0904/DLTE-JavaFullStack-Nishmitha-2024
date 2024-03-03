package org.example;

import java.util.Date;
import java.util.Scanner;

public class MyBankLoans implements MyBank {

    public static void main(String[] args) {
        MyBank myBank = new MyBankLoans();

        Scanner scanner = new Scanner(System.in);
        int choice;
        while(true) {
            System.out.println("MENU\n1. Add a new Loan\n2. Check available loans\n3. Check closed loans\n4. Exit");
            System.out.println("Enter your choice");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the number of loans you want to enter");
                    int numberOfLoans = scanner.nextInt();
                    Loan[] loans = new Loan[numberOfLoans];
                    for (int loan=0;loan<numberOfLoans;loan++) {
                        loans[loan] = myBank.addNewLoan();
                    }
                    break;
                case 2:
                    myBank.checkAvailableLoans();
                    break;
                case 3:
                    myBank.checkClosedLoans();
                    break;
                case 4:
                    System.exit(0);
            }
        }

    }


    @Override
    public Loan addNewLoan() {
        Loan loan = new Loan();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the loan number");
        loan.setLoanNumber(scanner.nextLong());
        System.out.println("Enter the loan amount");
        loan.setLoanAmount(scanner.nextDouble());
        System.out.println("Enter the loan date");
        loan.setLoanDate(new Date(scanner.next()));
        scanner.nextLine();
        System.out.println("Enter the loan status (open/closed)");
        loan.setLoanStatus(scanner.nextLine());
        System.out.println("Enter the borrower name");
        loan.setBorrowerName(scanner.nextLine());
        System.out.println("Enter the borrower contact");
        loan.setBorrowerContact(scanner.nextLong());
        return loan;
    }

    @Override
    public void checkAvailableLoans() {
        for (Loan loan : loans) {
            if (loan.getLoanStatus().equalsIgnoreCase("open")) {
                System.out.println(loan);
            }
        }
    }

    @Override
    public void checkClosedLoans() {
        for (Loan loan : loans) {
            if (loan.getLoanStatus().equalsIgnoreCase("closed")) {
                System.out.println(loan);
            }
        }
    }
}
