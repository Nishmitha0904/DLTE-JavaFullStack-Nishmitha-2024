package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDetails implements EmployeeDetailsInterface{

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        EmployeeFileOperations employeeFileOperations = new EmployeeFileOperations();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        while (true) {
            System.out.println(resourceBundle.getString("app.menu"));
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        char answer;
                        do {
                            employeeArrayList.add(employeeDetails.readDetails());
                            employeeFileOperations.writeIntoFile();
                            System.out.println(resourceBundle.getString("app.add.more"));
                            answer = scanner.next().charAt(0);
                        } while (answer == 'Y' || answer == 'y');
                        break;
                    case 2:
                        //employeeFileOperations.readFromFile();
                        employeeDetails.displayDetails(employeeArrayList);
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Enter a number");
            }
        }

    }

    @Override
    public Employee readDetails() {
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the employee ID");
        employee.setEmployeeID(scanner.nextLong());
        System.out.println("Enter the name of the employee");
        employee.setEmployeeName(readName());
        System.out.println("Enter the mobile number");
        employee.setEmployeeMobile(scanner.nextLong());
        while (!isValidMobileNumber(employee.getEmployeeMobile().toString())) {
            System.out.println("Invalid mobile number! Enter again");
            employee.setEmployeeMobile(scanner.nextLong());
        }

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

    @Override
    public void displayDetails(ArrayList<Employee> employees) {

        for (Employee employee : employees) {
            System.out.println("Employee ID: "+employee.getEmployeeID());
            System.out.println("Employee Name: "+employee.getEmployeeName());
            System.out.println("Employee Email: "+employee.getEmployeeEmail());
            System.out.println("Employee Mobile No.: "+employee.getEmployeeMobile());
            System.out.println("Employee Temporary Address: "+employee.getEmployeeTemporaryAddress());
            System.out.println("Employee Permanent Address: "+employee.getEmployeePermanentAddress());
        }
    }

    /*
    public static Employee readDetials() {
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the employee ID");
        employee.setEmployeeID(scanner.nextLong());
        System.out.println("Enter the name of the employee");
        employee.setEmployeeName(readName());
        System.out.println("Enter the mobile number");
        employee.setEmployeeMobile(scanner.nextLong());
        while (!isValidMobileNumber(employee.getEmployeeMobile().toString())) {
            System.out.println("Invalid mobile number! Enter again");
            employee.setEmployeeMobile(scanner.nextLong());
        }

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
*/
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
        System.out.print("House no/name: ");
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


//    public static void displayDetails(Employee employees) {
//        for (int employee=0;employee<employees.length;employee++) {
//            Employee emp = employees[employee];
//            System.out.println("Employee Details of employee "+(employee+1));
//            System.out.println("Employee ID: "+ emp.getEmployeeID());
//            System.out.println("Employee Name: "+emp.getEmployeeName());
//            System.out.println("Employee Email: "+emp.getEmployeeEmail());
//            System.out.println("Employee Mobile No.: "+emp.getEmployeeMobile());
//            System.out.println("Employee Temporary Address: "+emp.getEmployeeTemporaryAddress());
//            System.out.println("Employee Permanent Address: "+emp.getEmployeePermanentAddress());
//        }
//    }

/*
    public static void displayDetails(ArrayList<Employee> employees) {
        for (Employee employee: employees) {
            //System.out.println("Employee Details of employee "+(employee));
            System.out.println("Employee ID: "+ employee.getEmployeeID());
            System.out.println("Employee Name: "+employee.getEmployeeName());
            System.out.println("Employee Email: "+employee.getEmployeeEmail());
            System.out.println("Employee Mobile No.: "+employee.getEmployeeMobile());
            System.out.println("Employee Temporary Address: "+employee.getEmployeeTemporaryAddress());
            System.out.println("Employee Permanent Address: "+employee.getEmployeePermanentAddress());
        }
    }
 */

/*
    public static void displayDetails(ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println("Employee ID: "+employee.getEmployeeID());
            System.out.println("Employee Name: "+employee.getEmployeeName());
            System.out.println("Employee Email: "+employee.getEmployeeEmail());
            System.out.println("Employee Mobile No.: "+employee.getEmployeeMobile());
            System.out.println("Employee Temporary Address: "+employee.getEmployeeTemporaryAddress());
            System.out.println("Employee Permanent Address: "+employee.getEmployeePermanentAddress());
        }
    }

 */

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
