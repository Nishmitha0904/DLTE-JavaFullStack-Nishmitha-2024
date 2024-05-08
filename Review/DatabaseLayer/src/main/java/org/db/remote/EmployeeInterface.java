package org.db.remote;

import org.db.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeInterface {

    void save(Employee employee) throws SQLException;
    List<Employee> display() throws SQLException;
    List<Employee> displayByPincode(Long pincode) throws SQLException;
    Employee findById(Long empid);
}
