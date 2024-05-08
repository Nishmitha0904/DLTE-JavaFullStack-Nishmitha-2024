package org.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Implementations implements EmployeeDetailInterface {

    List<Employee> employeeList = new ArrayList<>();
    File filePath = new File("Employee.txt");
    Logger logger = LoggerFactory.getLogger("Implementations.class");


    public void writeIntoFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employeeList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ioException) {
            logger.warn("Error while writing to file");
        }

    }
    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        employeeList = (List<Employee>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }


    @Override
    public void insertDetails(List<Employee> employees) {
        try {
            if(filePath.exists()){
                readFromFile();
            }
            for (Employee employee: employees) {
                boolean alreadyExists = employeeList.stream().anyMatch(exist -> exist.getEmployeeID() == employee.getEmployeeID());
                if (alreadyExists) {
//                    System.out.println("Employee already exists");
                    throw new EmployeeException();
                } else {
                    employeeList.addAll(employees);
                }
            }
            writeIntoFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Employee> displayDetails() {
        try {
            readFromFile();
            return employeeList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee findEmployeeByPincode(Long pincode) {
        try {
            readFromFile();
            return employeeList.stream().filter(employee -> employee.getEmployeeTemporaryAddress().getPincode()==pincode).findAny().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

