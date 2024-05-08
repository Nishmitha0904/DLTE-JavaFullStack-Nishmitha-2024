package org.db;

import org.db.entity.Employee;
import org.db.exception.EmployeeException;
import org.db.middleware.DatabaseTarget;
import org.db.remote.EmployeeInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeServices {
    EmployeeInterface employeeInterface;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    ResourceBundle exceptionBundle = ResourceBundle.getBundle("exception");

//    public EmployeeServices(DatabaseTarget databaseTarget) {
//        employeeInterface = databaseTarget.getEmployeeInterface();
//    }

    public void callSave(Employee employee) throws SQLException {
        employeeInterface.save(employee);
    }

    public List<Employee> callDisplay() throws SQLException {
        return employeeInterface.display();
    }

    public List<Employee> callDisplayByPincode(Long pincode) throws SQLException{
        try {
            return employeeInterface.displayByPincode(pincode);
        } catch (EmployeeException empException) {
            System.out.println(exceptionBundle.getString("employee.not.found"));
        }
        return null;
    }

    public Employee callFindById(Long empid) {
        return employeeInterface.findById(empid);
    }

}
