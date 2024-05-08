package org.file;

import console.app.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileOperations {

    List<Employee> employeeList = new ArrayList<>();


    public void writeIntoFile(String filename, Employee employeeDetails) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
//        byte[] data = employeeDetails.getBytes();
//        fileOutputStream.write(data);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(employeeList);
        objectOutputStream.close();
        fileOutputStream.close();

    }

    public String readFromFile(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filename);
//        byte[] data = new byte[fileInputStream.available()];
//        fileInputStream.read(data);
//        return new String(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        employeeList= (List<Employee>) objectInputStream.readObject();
        return employeeList.toString();
    }
}
