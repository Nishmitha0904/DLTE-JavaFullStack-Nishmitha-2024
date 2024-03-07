package org.example;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyBankLoans implements MyBank {

    ArrayList<Loan> loanList = new ArrayList<>();
    public static void main(String[] args) {

        Loan loan1 = new Loan(16752361523423L, 50000.0, new Date(2024, 01, 12), "Open", "John", 7656765456L);
        Loan loan2 = new Loan(87463253635221L, 100000.0, new Date(2023, 10, 9), "Open", "Peter", 7647342389L);

        ArrayList<Loan> loanList = new ArrayList<>();

        MyBankLoans myBankLoans = new MyBankLoans();
        loanList.addAll(Stream.of(loan1,loan2).collect(Collectors.toList()));
        myBankLoans.writeIntoFile();
        Scanner scanner = new Scanner(System.in);
        int choice;
        while(true) {
            System.out.println("MENU\n1. Add a new Loan\n2. Check available loans\n3. Check closed loans\n4. Exit");
            System.out.println("Enter your choice");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    myBankLoans.addNewLoan(myBankLoans.insertLoan());
                    break;
                case 2:
                    myBankLoans.checkAvailableLoans();
                    break;
                case 3:
                    myBankLoans.checkClosedLoans();
                    break;
                default:
                    System.exit(0);
            }
        }

    }

    @Override
    public void readFromFile() {

        try {
            FileInputStream fileInputStream = new FileInputStream("loans.doc");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            loanList = (ArrayList<Loan>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeIntoFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("loans.doc");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(loanList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Loan addNewLoan(Loan loan) {
        readFromFile();
        loanList.add(loan);
        writeIntoFile();
        return null;
    }

    @Override
    public void checkAvailableLoans() {
        readFromFile();
        List<Loan> availableLoans = loanList.stream().filter(each -> each.getLoanStatus().equals("Open")).collect(Collectors.toList());
        availableLoans.forEach(Loan -> {
            System.out.println(Loan.toString());
        });
    }

    @Override
    public void checkClosedLoans() {
        readFromFile();
        List<Loan> closedLoans = loanList.stream().filter(each -> each.getLoanStatus().equals("Close")).collect(Collectors.toList());
        closedLoans.forEach(Loan -> {
            System.out.println(Loan.toString());
        });
    }

    public Loan insertLoan() {
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

}
