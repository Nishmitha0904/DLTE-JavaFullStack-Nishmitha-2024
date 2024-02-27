package org.example;

import java.util.Scanner;

public class MyBankLoans implements MyBank {

    public static void main(String[] args) {
        MyBank myBank = new MyBankLoans();

        Scanner scanner = new Scanner(System.in);
        int choice;
        while(true) {
            System.out.println("MENU\n1. Add a new Loan\n2. Check available loans\n3. Check closed loans\n 4. Exit");
            System.out.println("Enter your choice");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    myBank.addNewLoan();
                    break;
                case 2:
                    myBank.checkLoans();
                    break;
                case 3:
                    myBank.checkClosedLoans();
                    break;
            }
        }

    }

    @Override
    public void addNewLoan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the loan number");
        Long loanNo = scanner.nextLong();
        System.out.println("Enter the loan amount");
        Double loanAmount = scanner.nextDouble();
        System.out.println("Enter the loan date");
        String loanDate = scanner.next();
        System.out.println("Enter the loan status (open/closed)");
        String loanStatus = scanner.next();
        System.out.println("Enter the borrower name");
        String borrowerName = scanner.nextLine();
        System.out.println("Enter the borrower contact");
        Long borrowerContact = scanner.nextLong();
        Loan loan = new Loan(loanNo, loanAmount, loanDate, loanStatus, borrowerName, borrowerContact);
    }

    @Override
    public void checkLoans(Loan loan) {

    }

    @Override
    public void checkClosedLoans(Loan loan) {

    }
}
