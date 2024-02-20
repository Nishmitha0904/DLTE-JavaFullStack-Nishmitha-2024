package basics.service;

import java.util.Scanner;

public class PersonalLoan {
    public static void main(String[] args) {
        String name, dob, address, emailAddress, panNumber, maritalStatus, occupation;
        Long mobileNumber, aadharNumber, salaryAccountNumber;
        int numberOfDependents;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter full name");
        name =  scanner.nextLine();
        System.out.println("Enter the date of birth");
        dob = scanner.next();
        System.out.println("Enter the residential address");
        address = scanner.nextLine();
        System.out.println("Enter the mobile number");
        mobileNumber = scanner.nextLong();
        System.out.println("Enter Email ID");
        emailAddress = scanner.next();
        System.out.println("Enter Aadhaar number");
        aadharNumber = scanner.nextLong();
        System.out.println("Enter the PAN number");
        panNumber = scanner.next();
        System.out.println("Enter the type of occupation (salaried/self employed)");
        occupation = scanner.nextLine();
        if (occupation=="salaried") {
            System.out.println("Enter the salary account number");
            salaryAccountNumber = scanner.nextLong();
        }
        System.out.println("Enter marital status (married/single)");
        maritalStatus = scanner.next();
        System.out.println("Enter number of dependents");
        numberOfDependents = scanner.nextInt();

        scanner.close();
    }



}
