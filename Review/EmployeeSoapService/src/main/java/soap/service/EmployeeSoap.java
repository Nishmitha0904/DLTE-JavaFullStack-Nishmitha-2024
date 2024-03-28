package soap.service;

import org.db.EmployeeServices;
import org.db.entity.Employee;
import org.db.middleware.DatabaseTarget;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
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
    public void insertEmployee(@WebParam(name = "Employee") Employee employee) throws SQLException {
        services.callSave(employee);
    }

    @WebMethod
    public GroupOfEmployees readAll() throws SQLException {
        GroupOfEmployees employeeList = new GroupOfEmployees();
        List<Employee> employees = services.callDisplay();
        employeeList.setEmployees(employees);
        return employeeList;
    }

    @WebMethod
    public GroupOfEmployees readAllByPincode(Long pincode) {
        GroupOfEmployees employeeList = new GroupOfEmployees();
        List<Employee> employees = services.callDisplayByPincode(pincode);
        employeeList.setEmployees(employees);
        return employeeList;
    }

    @WebMethod
    @WebResult(name = "Employee")
    public Employee readById(Long id) {
        Employee employee = services.callFindById(id);
        return employee;
    }

}
