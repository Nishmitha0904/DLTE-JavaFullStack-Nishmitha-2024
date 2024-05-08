package web;

import org.db.EmployeeServices;
import org.db.entity.Employee;
import org.db.exception.EmployeeExistsException;
import org.db.middleware.DatabaseTarget;
import org.db.middleware.EmployeeImplementations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
//    EmployeeServices services;
    EmployeeImplementations implementations;
//    static Logger logger = LoggerFactory.getLogger("EmployeeSoap.class");

//    public EmployeeSoap() {
//        DatabaseTarget databaseTarget = new DatabaseTarget();
//        services=new EmployeeServices(databaseTarget);
//    }
    public EmployeeSoap() {
        implementations = new EmployeeImplementations();
    }

    @WebMethod
    public void insertEmployee(@WebParam(name = "Employee") Employee employee) {
        try {
            implementations.save(employee);
        } catch (EmployeeExistsException existException) {
//            logger.warn("Employee already Exists");
            System.out.println("Employee already exists!!");
//            break;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public GroupOfEmployees readAll() {
        GroupOfEmployees employeeList = new GroupOfEmployees();
        try {
            List<Employee> employees = implementations.display();
            employeeList.setEmployees(employees);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @WebMethod
    public GroupOfEmployees readAllByPincode(Long pincode) {
        GroupOfEmployees employeeList = new GroupOfEmployees();
        try {
            List<Employee> employees = implementations.displayByPincode(pincode);
            employeeList.setEmployees(employees);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    @WebMethod
    @WebResult(name = "Employee")
    public Employee readById(Long id) {
        Employee employee = implementations.findById(id);
        return employee;
    }

//    @WebMethod
//    public void insertEmployee(@WebParam(name = "Employee") Employee employee) {
//        try {
//            services.callSave(employee);
//        } catch (EmployeeExistsException existException) {
////            logger.warn("Employee already Exists");
//            System.out.println("Employee already exists!!");
////            break;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

//    @WebMethod
//    public GroupOfEmployees readAll() {
//        GroupOfEmployees employeeList = new GroupOfEmployees();
//        try {
//            List<Employee> employees = services.callDisplay();
//            employeeList.setEmployees(employees);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return employeeList;
//    }

//    @WebMethod
//    public GroupOfEmployees readAllByPincode(Long pincode) {
//        GroupOfEmployees employeeList = new GroupOfEmployees();
//        try {
//            List<Employee> employees = services.callDisplayByPincode(pincode);
//            employeeList.setEmployees(employees);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return employeeList;
//    }

//    @WebMethod
//    @WebResult(name = "Employee")
//    public Employee readById(Long id) {
//        Employee employee = services.callFindById(id);
//        return employee;
//    }
}
