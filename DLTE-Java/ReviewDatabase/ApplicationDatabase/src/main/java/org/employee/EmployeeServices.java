package org.employee;

import java.util.List;
import java.util.ResourceBundle;

public class EmployeeServices {
    EmployeeInterface employeeInterface;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    public EmployeeServices(DatabaseTarget databaseTarget) {
        employeeInterface = databaseTarget.getEmployeeInterface();
    }

    public void callSave(Employee employee) {
        employeeInterface.save(employee);
    }

    public List<Employee> callDisplay() {
        return employeeInterface.display();
    }
}
