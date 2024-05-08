package org.console.app;

import org.console.app.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.provider.certpath.OCSPResponse;
import web.Employee;
import web.EmployeeSoapService;
import web.Address;

import java.util.*;

public class App {
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static Logger logger = LoggerFactory.getLogger("App.class");
    static Validation validation = new Validation();

    static web.EmployeeSoapService employeeSoapService = new EmployeeSoapService();
    static web.EmployeeSoap soap = employeeSoapService.getEmployeeSoapPort();

    public static void main(String[] args) {
        Employee soapEmployee = new Employee();
        org.console.app.entity.Employee employee = new org.console.app.entity.Employee();
        List<Employee> employeeList = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(resourceBundle.getString("app.menu"));
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String answer;
                    do {
                        employee = inputDetails();
                        soapEmployee = translation(employee);
                        System.out.println(soapEmployee);
                        soap.insertEmployee(soapEmployee);
                        logger.info("Employee details saved");
                        System.out.println(resourceBundle.getString("app.add.more"));
                        answer = scanner.next();
                    } while (answer.equalsIgnoreCase("yes"));
                    logger.info(resourceBundle.getString("insert.success"));
                    break;
                case 2:
                    employeeList = soap.readAll().getEmployees();
                    displayDetails(employeeList);
                    System.out.println(resourceBundle.getString("display.success"));
                    logger.info(resourceBundle.getString("display.success"));
                    break;
                case 3:
                    List<Employee> employeeArrayList = new ArrayList<>();
                    System.out.println(resourceBundle.getString("app.enter.pincode"));
                    Long pincode = scanner.nextLong();
                    employeeArrayList = soap.readAllByPincode(pincode).getEmployees();
                    displayDetails(employeeArrayList);
                    logger.info(resourceBundle.getString("pin.display.success"));
                    break;
                default: System.exit(0);
            }
        }
    }

    public static Employee translation(org.console.app.entity.Employee employee) {
        Employee soapEmployee = new Employee();
        Address soapTempAddress = new Address();
        Address soapPermAddress = new Address();
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


    public static void displayDetails(List<web.Employee> employeeList) {
//        int length = employeeArrayList.size();
        int size = employeeList.size();
        for (int index=0;index<size;index++) {
            System.out.println("Details of employee "+(index+1));
            web.Employee employeeIndex = employeeList.get(index);
            System.out.println("Employee ID: " + employeeIndex.getEmployeeID());
            System.out.println("Employee Name: " + employeeIndex.getEmployeeName());
            System.out.println("Employee Email: " + employeeIndex.getEmployeeEmail());
            System.out.println("Employee Mobile: " + employeeIndex.getEmployeeMobile());
//            System.out.println("Employee Temporary Address: " + employeeIndex.getEmployeeTemporaryAddress());
            System.out.println("Employee Temporary Address: ");
            displayAddress(employeeIndex.getEmployeeTemporaryAddress());
//            System.out.println("Employee Permanent Address: " + employeeIndex.getEmployeePermanentAddress());
            System.out.println("Employee Permanent Address: ");
            displayAddress(employeeIndex.getEmployeePermanentAddress());
            System.out.println();
        }
    }
    public static void displayAddress(Address address) {
        System.out.println(address.getHouseName()+", "+address.getArea()+", "+address.getCity()+", "+address.getState()+"-"+address.getPincode());
    }

    public static org.console.app.entity.Employee inputDetails() {
//        Employee employee = new Employee();
        org.console.app.entity.Employee employee = new org.console.app.entity.Employee();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the Employee ID");
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

    public static org.console.app.entity.Address readAddress() {
        org.console.app.entity.Address addresses = new org.console.app.entity.Address();
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
