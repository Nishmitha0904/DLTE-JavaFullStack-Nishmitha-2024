package org.example;

import java.util.Scanner;

public class Employee {
    private Long employeeID;
    private String employeeName;
    private String employeeEmail;
    private Long employeeMobile;
//    private String employeeTemporaryAddress;
//    private String employeePermanentAddress;
    private Address employeeTemporaryAddress;
    private Address employeePermanentAddress;

    public Employee() {
    }

//    public Employee(Long employeeID, String employeeName, String employeeEmail, Long employeeMobile, String employeeTemporaryAddress, String employeePermanentAddress) {
    public Employee(Long employeeID, String employeeName, String employeeEmail, Long employeeMobile, Address employeeTemporaryAddress, Address employeePermanentAddress) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeeMobile = employeeMobile;
        this.employeeTemporaryAddress = employeeTemporaryAddress;
        this.employeePermanentAddress = employeePermanentAddress;
    }

//    public Employee(Long employeeID, String employeeName, String employeeEmail, Long employeeMobile) {
//        this.employeeID = employeeID;
//        this.employeeName = employeeName;
//        this.employeeEmail = employeeEmail;
//        this.employeeMobile = employeeMobile;
//    }
//
//    public Employee(String employeeTemporaryAddress, String employeePermanentAddress) {
//        this.employeeTemporaryAddress = employeeTemporaryAddress;
//        this.employeePermanentAddress = employeePermanentAddress;
//    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Long getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(Long employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

//    public String getEmployeeTemporaryAddress() {
//        return employeeTemporaryAddress;
//    }
//
//    public void setEmployeeTemporaryAddress(String employeeTemporaryAddress) {
//        this.employeeTemporaryAddress = employeeTemporaryAddress;
//    }
//
//    public String getEmployeePermanentAddress() {
//        return employeePermanentAddress;
//    }
//
//    public void setEmployeePermanentAddress(String employeePermanentAddress) {
//        this.employeePermanentAddress = employeePermanentAddress;
//    }


    public Address getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    public void setEmployeeTemporaryAddress(Address employeeTemporaryAddress) {
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public Address getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    public void setEmployeePermanentAddress(Address employeePermanentAddress) {
        this.employeePermanentAddress = employeePermanentAddress;
    }
}
