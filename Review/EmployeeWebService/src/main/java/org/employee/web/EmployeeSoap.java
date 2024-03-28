package org.employee.web;

import org.db.EmployeeServices;
import org.db.entity.Employee;
import org.db.middleware.DatabaseTarget;
import org.db.remote.StorageTarget;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding (style = SOAPBinding.Style.RPC)
public class EmployeeSoap {
    EmployeeServices services;

    public EmployeeSoap() {
        DatabaseTarget databaseTarget = new DatabaseTarget();
        services=new EmployeeServices(databaseTarget);
    }

    @WebMethod
    public void insertEmployee(@WebParam(name = "Employee")Employee employee) throws SQLException {
        services.callSave(employee);
    }

    @WebMethod
    public ArrayList<Employee> readAll() throws SQLException {
        ArrayList<Employee> employeeList = (ArrayList<Employee>) services.callDisplay();
        return employeeList;
    }

    @WebMethod
    public ArrayList<Employee> readAllByPincode(Long pincode) {
        ArrayList<Employee> employeeList = (ArrayList<Employee>) services.callDisplayByPincode(pincode);
        return  employeeList;
    }

    @WebMethod
    @WebResult(name = "Employee")
    public Employee readById(Long id) {
        Employee employee = services.callFindById(id);
        return employee;
    }
}
