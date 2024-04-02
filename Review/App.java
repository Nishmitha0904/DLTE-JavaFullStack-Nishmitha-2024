package org.console.app.console;



import org.console.app.entity.Address;
import org.console.app.entity.Employee;
//import org.db.entity.Employee;
//import org.db.entity.Address;
import org.console.app.validation.Validation;
import org.db.exception.ConnectionException;
import org.db.exception.EmployeeException;
import org.db.exception.EmployeeExistsException;
import org.db.middleware.DatabaseTarget;
import org.db.EmployeeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static Logger logger = LoggerFactory.getLogger("App.class");
    static Validation validation = new Validation();

    static DatabaseTarget databaseTarget = new DatabaseTarget();
    static EmployeeServices services = new EmployeeServices(databaseTarget);

    public static void main( String[] args )
    {
        List<org.db.entity.Employee> employeeArrayList = new ArrayList<>();
        Employee employee = new Employee();
        org.db.entity.Employee dbEmployee = new org.db.entity.Employee();
//        org.db.entity.Address dbTempAddress = new org.db.entity.Address();
//        org.db.entity.Address dbPermAddress = new org.db.entity.Address();
//        Scanner scanner = new Scanner(System.in);
        App app = new App();
        try {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println(resourceBundle.getString("app.menu"));
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        String answer;
                        do {
                            try {
                                employee = inputDetails();
                                dbEmployee = translation(employee);
                                services.callSave(dbEmployee);
                                logger.info("Employee details saved");
                            } catch (EmployeeExistsException existException) {
                                logger.warn("Employee already Exists");
                                System.out.println("Employee already exists!!");
                                break;
                            } catch (SQLException exception) {
                                logger.error("SQL Exception");
                            }
                            System.out.println(resourceBundle.getString("app.add.more"));
                            answer = scanner.next();
                        } while (answer.equalsIgnoreCase("yes"));
                        logger.info(resourceBundle.getString("insert.success"));
                        break;
                    case 2:
                        try {
                            employeeArrayList = services.callDisplay();
                            displayDetails(employeeArrayList);
                            System.out.println(resourceBundle.getString("display.success"));
                            logger.info(resourceBundle.getString("display.success"));
                        } catch (EmployeeException employeeException) {
                            System.out.println("No employee details found");
                        } catch (SQLException exception) {
                            logger.error("SQL Exception");
                        }

                        break;
                    case 3:
                        System.out.println(resourceBundle.getString("app.enter.pincode"));
                        logger.info("Pincode entered");
                        try {
                            Long pincode = scanner.nextLong();
                            employeeArrayList = services.callDisplayByPincode(pincode);
                            System.out.println(employeeArrayList);
                            displayDetails(employeeArrayList);
                            logger.info(resourceBundle.getString("pin.display.success"));
                        } catch (EmployeeException employeeException) {
                            System.out.println(resourceBundle.getString("employee.not.found"));
                            break;
                        } catch (SQLException exception) {
                            logger.error("SQL Exception");
                        }

                        break;
                    case 4:
                        logger.info("User exit from the application");
                        System.exit(0);
                    default:
                        logger.info("User entered an invalid choice");
                        System.out.println("Invalid choice");
                }
            }
        } catch (ConnectionException exception) {
            System.out.println(exception.getMessage());
        }
//        catch (SQLException exception) {
//            logger.error("SQL Exception");
//        }

    }


    public static org.db.entity.Employee translation(Employee employee) {
        org.db.entity.Employee dbEmployee = new org.db.entity.Employee();
        org.db.entity.Address dbTempAddress = new org.db.entity.Address();
        org.db.entity.Address dbPermAddress = new org.db.entity.Address();
        dbEmployee.setEmployeeID(employee.getEmployeeID());
        dbEmployee.setEmployeeName(employee.getEmployeeName());
        dbEmployee.setEmployeeEmail(employee.getEmployeeEmail());
        dbEmployee.setEmployeeMobile(employee.getEmployeeMobile());
        dbTempAddress.setHouseName(employee.getEmployeeTemporaryAddress().getHouseName());
        dbTempAddress.setArea(employee.getEmployeeTemporaryAddress().getArea());
        dbTempAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        dbTempAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        dbTempAddress.setPincode(employee.getEmployeeTemporaryAddress().getPincode());
        dbPermAddress.setHouseName(employee.getEmployeePermanentAddress().getHouseName());
        dbPermAddress.setArea(employee.getEmployeePermanentAddress().getArea());
        dbPermAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        dbPermAddress.setState(employee.getEmployeePermanentAddress().getState());
        dbPermAddress.setPincode(employee.getEmployeePermanentAddress().getPincode());
        dbEmployee.setEmployeeTemporaryAddress(dbTempAddress);
        dbEmployee.setEmployeePermanentAddress(dbPermAddress);
        logger.info("Translation done");
        return dbEmployee;
    }

    public static void displayDetails(List<org.db.entity.Employee> employeeArrayList) {
        int length = employeeArrayList.size();
        for (int index=0;index<length;index++) {
            System.out.println("Details of employee "+(index+1));
            org.db.entity.Employee employeeIndex = employeeArrayList.get(index);
            System.out.println("Employee ID: "+employeeIndex.getEmployeeID());
            System.out.println("Employee Name: "+employeeIndex.getEmployeeName());
            System.out.println("Employee Email: "+employeeIndex.getEmployeeEmail());
            System.out.println("Employee Mobile: "+employeeIndex.getEmployeeMobile());
            System.out.println("Employee Temporary Address: "+employeeIndex.getEmployeeTemporaryAddress());
            System.out.println("Employee Permanent Address: "+employeeIndex.getEmployeePermanentAddress());
            System.out.println();
        }
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
