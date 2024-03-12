package employee.file;

import org.entity.Employee;
import org.entity.EmployeeDetailInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Implementations implements EmployeeDetailInterface {

    List<Employee> employeeList = new ArrayList<>();
    File filePath = new File("employee.doc");

    public void writeIntoFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(employeeList);
        objectOutputStream.close();
        fileOutputStream.close();
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
            readFromFile();
            for (Employee employee: employees) {
                boolean employeeExists = employeeList.stream().anyMatch(exist -> exist.getEmployeeID() == employee.getEmployeeID());
                if (employeeExists) {
                    System.out.println("Employee already exists");
                } else {
                    employeeList.addAll(employees);
                }
            }
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
