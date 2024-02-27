package org.example;

import java.util.Scanner;

public class Employee {
    String name, temporaryAddress, permanentAddress;

    public Employee() {
    }

    public Employee(String name, String temporaryAddress, String permanentAddress) {
        this.name = name;
        this.temporaryAddress = temporaryAddress;
        this.permanentAddress = permanentAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", temporaryAddress='" + temporaryAddress + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();
        System.out.println("Mention the employee name");
        employee.name = scanner.nextLine();
        System.out.println("Provide temporary address");
        employee.temporaryAddress = scanner.nextLine();
        System.out.println("Provide permanent address");
        employee.permanentAddress = scanner.nextLine();

        //System.out.println(employee);
        System.out.println("Employee Name: "+employee.name+"\nTemporary Address: "+employee.temporaryAddress+"\nPermanent Address: "+employee.permanentAddress);
    }
}
