package org.soap.client;

import web.Address;
import web.Employee;
import web.EmployeeSoapService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    static web.EmployeeSoapService employeeSoapService = new EmployeeSoapService();
    static web.EmployeeSoap soap = employeeSoapService.getEmployeeSoapPort();

    public static void main( String[] args )
    {
//        web.Employee employee = new Employee();
//        employee.setEmployeeID(6573647L);
//        employee.setEmployeeName("Nishmitha");
//        employee.setEmployeeEmail("nish@gmail.com");
//        employee.setEmployeeMobile(8756473837L);
//
//        Address tempAddress = new Address();
//        tempAddress.setHouseName("pg");
//        tempAddress.setArea("Mailasandra");
//        tempAddress.setCity("Bangalore");
//        tempAddress.setState("Karnataka");
//        tempAddress.setPincode(560059L);
//
//        Address permAddress = new Address();
//        permAddress.setHouseName("3-67");
//        permAddress.setArea("Cherkady");
//        permAddress.setCity("Udupi");
//        permAddress.setState("Karnataka");
//        permAddress.setPincode(576215L);
//
//        employee.setEmployeeTemporaryAddress(tempAddress);
//        employee.setEmployeePermanentAddress(permAddress);
//
//        soap.insertEmployee(employee);

        List<Employee> employeeList = new ArrayList<>();
        employeeList = soap.readAll().getEmployees();
        System.out.println(employeeList);

//        Scanner scanner = new Scanner(System.in);
//        Long pincode = scanner.nextLong();
//        employeeList = soap.readAllByPincode(pincode).getEmployees();
//        System.out.println(employeeList);

    }
}
