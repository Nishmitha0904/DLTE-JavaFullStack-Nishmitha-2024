package org.example;


import java.util.Scanner;

public class EmployeeDetails1 {
    public static void main(String[] args) {
        EmployeeDetails1 employeeDetails = new EmployeeDetails1();
        String employeeTemporaryAddress, employeePermanentAdress, employeeEmail;
        Long employeeMobile;
        employeeDetails.readDetials();
    }
    public void readName() {
        String[] name = new String[3];
        String employeeFirstName, employeeMiddleName, employeeLastName;
        Scanner scanner = new Scanner(System.in);
        System.out.print("First Name: ");
        employeeFirstName = scanner.nextLine();
        name[0] = employeeFirstName;
        System.out.print("Middle Name: ");
        employeeMiddleName = scanner.nextLine();
        name[1] = employeeMiddleName;
        System.out.print("Last Name: ");
        employeeLastName = scanner.nextLine();
        name[2] = employeeLastName;
    }

    public String readAddress() {
        String homeAddress, area, city, state;
        int pincode;
        String address ="";
        Scanner scanner = new Scanner(System.in);
        System.out.print("House no./name: ");
        homeAddress = scanner.nextLine();
        System.out.print("Area: ");
        area = scanner.nextLine();
        System.out.print("City: ");
        city = scanner.nextLine();
        System.out.print("State: ");
        state = scanner.nextLine();
        System.out.print("Pincode: ");
        pincode = scanner.nextInt();
        address = homeAddress+", "+area+", "+city+", "+state+"-"+pincode;
        return address;
    }

    public void readDetials() {
        int employeeID;
        String temporaryAddress, permanentAddress;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the employee ID");
        employeeID = scanner.nextInt();
        System.out.println("Enter the name of the employee");
        readName();
        System.out.println("Enter the temporary address");
        temporaryAddress = readAddress();
        System.out.println("Enter the permanent address");
        permanentAddress = readAddress();
    }

}
