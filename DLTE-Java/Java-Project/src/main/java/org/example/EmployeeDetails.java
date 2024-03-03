package org.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDetails {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of Employees");
        int numberOfEmployees = scanner.nextInt();
        Employee[] employees = new Employee[numberOfEmployees];
        for (int index=0;index<numberOfEmployees;index++) {
            employees[index]=readDetials();
        }
            displayDetails(employees);
    }

    public static Employee readDetials() {
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the employee ID");
        employee.setEmployeeID(scanner.nextLong());
        System.out.println("Enter the name of the employee");
        employee.setEmployeeName(readName());
        System.out.println("Enter the mobile number");
        employee.setEmployeeMobile(scanner.nextLong());

        System.out.println("Enter the email ID");
        employee.setEmployeeEmail(scanner.next());
        while (!isValidEmail(employee.getEmployeeEmail())) {
            System.out.println("Invalid Email! Enter again");
            employee.setEmployeeEmail(scanner.next());
        }
        System.out.println("Enter the temporary address");
        employee.setEmployeeTemporaryAddress(readAddress());
        System.out.println("Enter the permanent address");
        employee.setEmployeePermanentAddress(readAddress());
        return employee;
    }

    public static String readName() {
        String employeeFirstName, employeeMiddleName, employeeLastName;
        String name = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("First Name: ");
        employeeFirstName = scanner.nextLine();
        System.out.print("Middle Name: ");
        employeeMiddleName = scanner.nextLine();
        System.out.print("Last Name: ");
        employeeLastName = scanner.nextLine();
        name = employeeFirstName+" "+employeeMiddleName+" "+employeeLastName;
        return name;
    }

    public static Address readAddress() {
        Address addresses = new Address();
        Scanner scanner = new Scanner(System.in);
        System.out.print("House no./name: ");
        addresses.setHomeAddress(scanner.nextLine());
        System.out.print("Area: ");
        addresses.setArea(scanner.nextLine());
        System.out.print("City: ");
        addresses.setCity(scanner.nextLine());
        System.out.print("State: ");
        addresses.setState(scanner.nextLine());
        System.out.print("Pincode: ");
        addresses.setPincode(scanner.nextLong());
        return addresses;
    }


    public static void displayDetails(Employee[] employees) {
        for (int employee=0;employee<employees.length;employee++) {
            Employee emp = employees[employee];
            System.out.println("Employee Details of employee "+(employee+1));
            System.out.println("Employee ID: "+ emp.getEmployeeID());
            System.out.println("Employee Name: "+emp.getEmployeeName());
            System.out.println("Employee Email: "+emp.getEmployeeEmail());
            System.out.println("Employee Mobile No.: "+emp.getEmployeeMobile());
            System.out.println("Employee Temporary Address: "+emp.getEmployeeTemporaryAddress());
            System.out.println("Employee Permanent Address: "+emp.getEmployeePermanentAddress());
        }
    }


//    public static void displayDetails(Employee[] employees) {
//        for (Employee employee: employees) {
//            //System.out.println("Employee Details of employee "+(employee));
//            System.out.println("Employee ID: "+ employee.getEmployeeID());
//            System.out.println("Employee Name: "+employee.getEmployeeName());
//            System.out.println("Employee Email: "+employee.getEmployeeEmail());
//            System.out.println("Employee Mobile No.: "+employee.getEmployeeMobile());
//            System.out.println("Employee Temporary Address: "+employee.getEmployeeTemporaryAddress());
//            System.out.println("Employee Permanent Address: "+employee.getEmployeePermanentAddress());
//        }
//    }

    public static Boolean isValidEmail(String borrowerEmail) {
        String emailExpression = "^[A-Za-z0-9+_.-]+@[a-zA-Z]{3,}+\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(emailExpression);
        Matcher matcher = pattern.matcher(borrowerEmail);
        return matcher.matches();
    }
    public static Boolean isValidMobileNumber(String mobileNumber) {
        String mobileExpression = "\\d{10}";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

}
