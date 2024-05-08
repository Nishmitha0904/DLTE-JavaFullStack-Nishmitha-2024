package org.employee.dao.employeespringdao.interfaces;

import org.employee.dao.employeespringdao.entity.Employee;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeInterface {

    String save(Employee employee) throws SQLSyntaxErrorException;
    List<Employee> findAllEmployees();
    List<Employee> findByPincode(Long pincode);
    Employee findById(Long id);
}
