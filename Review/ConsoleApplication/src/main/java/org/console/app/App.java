package org.console.app;



//import org.console.app.entity.Address;
//import org.console.app.entity.Employee;
import org.db.entity.Employee;
import org.db.entity.Address;
import org.db.middleware.DatabaseTarget;
import org.db.EmployeeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static Logger logger = LoggerFactory.getLogger("App.class");
    static Validation validation = new Validation();

    public static void main( String[] args )
    {

        DatabaseTarget databaseTarget = new DatabaseTarget();
        EmployeeServices services = new EmployeeServices(databaseTarget);
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        Employee employee = new Employee();
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
                        employee = inputDetails();
                        services.callSave(employee);

                        System.out.println(resourceBundle.getString("app.add.more"));
                        answer = scanner.next();
                    } while (answer.equalsIgnoreCase("yes"));
                    logger.info(resourceBundle.getString("insert.success"));
                    break;
                case 2:
                    //System.out.println(implementations.displayDetails());
                    System.out.println(services.callDisplay());
                    break;
                case 3:
//                        System.out.println(resourceBundle.getString("app.enter.pincode"));
//                        System.out.println(implementations.findEmployeeByPincode(scanner.nextLong()));;
                    System.out.println(resourceBundle.getString("app.enter.pincode"));
                    System.out.println(services.callDisplayByPincode(scanner.nextLong()));
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
        while (!validation.isValidMobileNumber(employee.getEmployeeMobile().toString())) {
            System.out.println(resourceBundle.getString("mobile.invalid"));
            employee.setEmployeeMobile(scanner.nextLong());
        }

        System.out.println("Enter the email ID");
        employee.setEmployeeEmail(scanner.next());
        while (!validation.isValidEmail(employee.getEmployeeEmail())) {
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
        return addresses;
    }



}
