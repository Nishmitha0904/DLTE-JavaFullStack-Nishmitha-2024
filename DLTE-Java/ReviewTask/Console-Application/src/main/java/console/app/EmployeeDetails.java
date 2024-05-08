package console.app;

import org.file.FileOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDetails implements EmployeeDetailInterface {
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    FileOperations fileOperations = new FileOperations();
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        while (true) {
            System.out.println(resourceBundle.getString("app.menu"));
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    char answer;
                    do {
                        //employeeArrayList.add(employeeDetails.readDetails());
                        employeeDetails.readDetails();
                        System.out.println(resourceBundle.getString("app.add.more"));
                        answer = scanner.next().charAt(0);
                    } while (answer == 'Y' || answer == 'y');
                    break;
                case 2:
                    employeeDetails.displayDetails();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    @Override
    public void readDetails() {
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


        try {
            fileOperations.writeIntoFile("employee.doc", employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayDetails() {
        String details;
        try {
            details = fileOperations.readFromFile("employee.doc");
            if (details != null) {
                System.out.println(details);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findEmployeeByPincode(Long pincode) {
        String details = null;

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
    }
}
