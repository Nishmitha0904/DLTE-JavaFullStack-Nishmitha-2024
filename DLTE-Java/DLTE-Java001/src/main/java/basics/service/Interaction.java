package basics.service;

//Command line interaction: Car loan

/*
Data:
Personal details: name, aadhaar, pan, address, mobile, email
Income: Salaried or self-employed(ITR files submitted)
 */

import java.util.Scanner;

public class Interaction {
    public static void main(String[] args) {
        String borrowerName="", borrowerPan="", borrowerAddress="", borrowerEmail="", borrowerIncomeType="";
        Long mobileNumber=0L, aadhaar=0L;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to MyBank!");
        System.out.println("Fill your name ");
        borrowerName = scanner.nextLine();
        System.out.println("Fill your aadhaar number ");
        aadhaar = scanner.nextLong();
        System.out.println("Enter the PAN ");
        borrowerPan = scanner.next();
        System.out.println("Let us know Income type (Salaried or self employed) ");
        borrowerIncomeType = scanner.nextLine();
        System.out.println("Mention mobile number ");
        mobileNumber = scanner.nextLong();
        System.out.println("Enter the email address ");
        borrowerEmail = scanner.next();


        System.out.println("Dear " + borrowerName + " Thanks for showing interest on taking a car loan in MyBank. Your application has submitted and further details will be mailed to you on " + borrowerEmail + " or SMS to "+mobileNumber);
    }
}
