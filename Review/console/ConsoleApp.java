package org.console.app.console;

import org.console.app.entity.Address;
import org.console.app.entity.Employee;
import org.console.app.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soap.service.EmployeeSoapService;
import soap.service.EmployeeSoap;
import soap.service.GroupOfEmployees;

import java.util.*;

public class ConsoleApp {
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static Logger logger = LoggerFactory.getLogger("ConsoleApp.class");
    static Validation validation = new Validation();

    static EmployeeSoapService employeeSoapService = new EmployeeSoapService();
    static EmployeeSoap employeeSoap = employeeSoapService.getEmployeeSoapPort();

    public static void main(String[] args) {
        List<soap.service.Employee> employeeList = new ArrayList<>();
        soap.service.GroupOfEmployees groupOfEmployees = new GroupOfEmployees();
        Employee employee = new Employee();
        soap.service.Employee soapEmployee = new soap.service.Employee();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(resourceBundle.getString("app.menu"));
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: String answer="";
                do {
                        employee = inputDetails();
                        soapEmployee = translation(employee);
//                        employeeSoap.insertEmployee(soapEmployee);
                        System.out.println(resourceBundle.getString("app.add.more"));
                        answer = scanner.next();
                } while (answer.equalsIgnoreCase("yes"));
                    logger.info(resourceBundle.getString("insert.success"));
                    break;
                case 2:
//                    groupOfEmployees = employeeSoap.readAll();
//                    displayDetails(employeeList);
//                    System.out.println(groupOfEmployees.getEmployees());
//                    displayDetails(groupOfEmployees);
                    employeeList = employeeSoap.readAll().getEmployees();
                    displayDetails(employeeList);
                    System.out.println(resourceBundle.getString("display.success"));
                    logger.info(resourceBundle.getString("display.success"));
                    break;

            }
        }


    }

    public static void displayDetails(List<soap.service.Employee> employeeList) {
//        int length = employeeArrayList.size();
        int size = employeeList.size();
        for (int index=0;index<size;index++) {
            System.out.println("Details of employee "+(index+1));
        soap.service.Employee employeeIndex = employeeList.get(index);
        System.out.println("Employee ID: " + employeeIndex.getEmployeeID());
        System.out.println("Employee Name: " + employeeIndex.getEmployeeName());
        System.out.println("Employee Email: " + employeeIndex.getEmployeeEmail());
        System.out.println("Employee Mobile: " + employeeIndex.getEmployeeMobile());
        System.out.println("Employee Temporary Address: " + employeeIndex.getEmployeeTemporaryAddress());
        System.out.println("Employee Permanent Address: " + employeeIndex.getEmployeePermanentAddress());
        System.out.println();
        }
    }
        public static soap.service.Employee translation(Employee employee) {
       soap.service.Employee soapEmployee = new soap.service.Employee();
        soap.service.Address soapTempAddress = new soap.service.Address();
        soap.service.Address soapPermAddress = new soap.service.Address();
        soapEmployee.setEmployeeID(employee.getEmployeeID());
        soapEmployee.setEmployeeName(employee.getEmployeeName());
        soapEmployee.setEmployeeEmail(employee.getEmployeeEmail());
        soapEmployee.setEmployeeMobile(employee.getEmployeeMobile());
        soapTempAddress.setHouseName(employee.getEmployeeTemporaryAddress().getHouseName());
        soapTempAddress.setArea(employee.getEmployeeTemporaryAddress().getArea());
        soapTempAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        soapTempAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        soapTempAddress.setPincode(employee.getEmployeeTemporaryAddress().getPincode());
        soapPermAddress.setHouseName(employee.getEmployeePermanentAddress().getHouseName());
        soapPermAddress.setArea(employee.getEmployeePermanentAddress().getArea());
        soapPermAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        soapPermAddress.setState(employee.getEmployeePermanentAddress().getState());
        soapPermAddress.setPincode(employee.getEmployeePermanentAddress().getPincode());
//        soapEmployee.setEmployeeTemporaryAddress(soapTempAddress);
//        soapEmployee.setEmployeePermanentAddress(soapPermAddress);
        logger.info("Translation done");
        return soapEmployee;
    }

    public static Employee inputDetails() {
        Long employeeID;
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the employee ID");
//        employeeID = scanner.nextLong();
//        employee.setEmployeeID(employeeID);
        while (true) {
            try {
                System.out.println("Enter the Employee ID");
                //employeeID = scanner.nextLong();
                employee.setEmployeeID(scanner.nextLong());
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn(resourceBundle.getString("employee.id.invalid"));
                System.out.println(resourceBundle.getString("employee.id.invalid"));
            }
        }
        System.out.println("Enter the name of the employee");
        employee.setEmployeeName(readName());
        System.out.println("Enter the mobile number");
//        String mobile = scanner.nextLine();
//        employee.setEmployeeMobile(scanner.nextLong());
        while (true) {
            try {
                employee.setEmployeeMobile(scanner.nextLong());
                while (!validation.isValidMobileNumber(employee.getEmployeeMobile().toString())) {
                    logger.info("Invalid mobile number entered");
                    System.out.println(resourceBundle.getString("mobile.invalid"));
                    employee.setEmployeeMobile(scanner.nextLong());
                }
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Phone Number");
                System.out.println(resourceBundle.getString("app.validation.invalidPhoneNumber"));
            }
        }
        System.out.println("Enter the email ID");
        employee.setEmployeeEmail(scanner.next());
        while (!validation.isValidEmail(employee.getEmployeeEmail())) {
            logger.info("Invalid email entered");
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
        System.out.print("House no/name: ");
        addresses.setHouseName(scanner.nextLine());
        System.out.print("Area: ");
        addresses.setArea(scanner.nextLine());
        System.out.print("City: ");
        addresses.setCity(scanner.nextLine());
        System.out.print("State: ");
        addresses.setState(scanner.nextLine());
        System.out.print("Pincode: ");
        addresses.setPincode(scanner.nextLong());
        while (!validation.isValidPincode(addresses.getPincode())) {
            System.out.println("Invalid pincode! Enter again");
            addresses.setPincode(scanner.nextLong());
        }
        return addresses;
    }

}
