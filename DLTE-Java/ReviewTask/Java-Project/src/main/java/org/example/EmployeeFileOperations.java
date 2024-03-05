package org.example;

import java.io.*;
import java.util.ArrayList;

public class EmployeeFileOperations {

    private ArrayList<Employee> employeeList;
    public static void main(String[] args) {

    }

    public void writeIntoFile() throws IOException {
        File employeeFile = new File("employees.doc");
        FileOutputStream fileOutputStream = new FileOutputStream(employeeFile, true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(employeeList);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("employees.doc");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        employeeList = (ArrayList<Employee>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }
}
