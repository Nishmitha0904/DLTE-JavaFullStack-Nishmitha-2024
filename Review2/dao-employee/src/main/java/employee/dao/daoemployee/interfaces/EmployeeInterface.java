package employee.dao.daoemployee.interfaces;

import employee.dao.daoemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface EmployeeInterface {

    Employee save(Employee employee) throws SQLSyntaxErrorException;
    List<Employee> findAllEmployees();
    List<Employee> findByPincode(Long pincode);
    Employee findById(Long id);
}

