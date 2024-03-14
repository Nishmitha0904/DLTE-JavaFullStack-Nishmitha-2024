package org.employee;

import java.util.List;

public interface EmployeeInterface {
    void save(Employee employee);
    List<Employee> display();
    //List<Employee> findByPincode(Long pincode);
}
