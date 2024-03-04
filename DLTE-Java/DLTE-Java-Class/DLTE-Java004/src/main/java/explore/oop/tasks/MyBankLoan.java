package explore.oop.tasks;

import java.util.Scanner;

public class MyBankLoan implements MyBank {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("MENU\n1. Add a new Loan\n2. Check available loans\n3. Check closed loans");
        System.out.println("Enter your choice");
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter the loan number");

                System.out.println("Enter the loan amount");
                System.out.println("Enter the loan date");
                System.out.println("Enter the loan status (open/closed)");
                System.out.println("Enter the borrower name");
                System.out.println("Enter the borrower contact");
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    @Override
    public void addNewLoan() {

    }

    @Override
    public void checkLoans() {

    }

    @Override
    public void checkClosedLoans() {

    }
}
