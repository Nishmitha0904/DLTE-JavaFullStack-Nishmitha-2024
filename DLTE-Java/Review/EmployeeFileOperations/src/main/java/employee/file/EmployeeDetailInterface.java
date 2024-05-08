package employee.file;

import java.util.List;

public interface EmployeeDetailInterface {
    void insertDetails(List<Employee> employees);
    List<Employee> displayDetails();
    Employee findEmployeeByPincode(Long pincode);
}
