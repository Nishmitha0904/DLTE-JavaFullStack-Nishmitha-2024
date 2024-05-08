package org.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger = LoggerFactory.getLogger("App.class");
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main( String[] args )
    {
        Implementations implementations = new Implementations();
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        App app = new App();
        while (true) {
            System.out.println(resourceBundle.getString("app.menu"));
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String answer;
                    do {
                        //employeeArrayList.add(employeeDetails.readDetails());
                        employeeArrayList.add(inputDetails());
                        try {
                            implementations.insertDetails(employeeArrayList);
                        } catch (EmployeeException exception) {
                            logger.warn(resourceBundle.getString("employee.exists"));
                        }

                        System.out.println(resourceBundle.getString("app.add.more"));
                        answer = scanner.next();
                    } while (answer.equalsIgnoreCase("yes"));
                    logger.info(resourceBundle.getString("insert.success"));
                    break;
                case 2:
                    System.out.println(implementations.displayDetails());
                    break;
                case 3:
                    System.out.println(resourceBundle.getString("app.enter.pincode"));
                    System.out.println(implementations.findEmployeeByPincode(scanner.nextLong()));;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static Employee inputDetails() {
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the employee ID");
        employee.setEmployeeID(scanner.nextLong());
        System.out.println("Enter the name of the employee");
        employee.setEmployeeName(readName());
        System.out.println("Enter the mobile number");
        employee.setEmployeeMobile(scanner.nextLong());
        while (!isValidMobileNumber(employee.getEmployeeMobile().toString())) {
            System.out.println(resourceBundle.getString("mobile.invalid"));
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
    }}
