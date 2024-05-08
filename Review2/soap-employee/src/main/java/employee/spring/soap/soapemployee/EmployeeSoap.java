package employee.spring.soap.soapemployee;

import com.sun.org.apache.xerces.internal.impl.XMLEntityScanner;
import employee.dao.daoemployee.exception.EmployeeException;
import employee.dao.daoemployee.exception.EmployeeExistsException;
import employee.dao.daoemployee.interfaces.EmployeeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

@Endpoint
@ComponentScan("employee.dao.daoemployee")
public class EmployeeSoap {

    private final String url="http://employee.services";
    Logger logger = LoggerFactory.getLogger(EmployeeSoap.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Autowired
    private EmployeeInterface employeeInterface;

    @PayloadRoot(namespace = url, localPart = "findAllEmployeesRequest")
    @ResponsePayload
    public FindAllEmployeesResponse findAll(@RequestPayload FindAllEmployeesRequest findAllEmployeesRequest) {
        FindAllEmployeesResponse response = new FindAllEmployeesResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> employees = new ArrayList<>();

        List<employee.dao.daoemployee.entity.Employee> daoEmployee = null;
        try {
            daoEmployee = employeeInterface.findAllEmployees();
        } catch (EmployeeException exception) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("No employee details!");
        }

        Iterator<employee.dao.daoemployee.entity.Employee> iterator = daoEmployee.iterator();
        while (iterator.hasNext()) {
            Employee current = new Employee();
            BeanUtils.copyProperties(iterator.next(),current);
            employees.add(current);
        }
        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage("Employee details displayed");
        response.setServiceStatus(serviceStatus);
        response.getEmployee().addAll(employees);
        return response;
    }

    @PayloadRoot(namespace = url, localPart = "saveRequest")
    @ResponsePayload
    public SaveResponse saveEmployee(@RequestPayload SaveRequest saveRequest) {
        SaveResponse response = new SaveResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Employee employee = saveRequest.getEmployee();
        try {
            employee.dao.daoemployee.entity.Employee daoEmployee = new employee.dao.daoemployee.entity.Employee();
            employee.dao.daoemployee.entity.Address daoTempAddress = new employee.dao.daoemployee.entity.Address();
            employee.dao.daoemployee.entity.Address daoPermAddress = new employee.dao.daoemployee.entity.Address();
            BeanUtils.copyProperties(employee, daoEmployee);
            BeanUtils.copyProperties(employee.getEmployeeTemporaryAddress(), daoTempAddress);
            BeanUtils.copyProperties(employee.getEmployeePermanentAddress(), daoPermAddress);
            daoEmployee.setEmployeeTemporaryAddress(daoTempAddress);
            daoEmployee.setEmployeePermanentAddress(daoPermAddress);
            employeeInterface.save(daoEmployee);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("add.success"));
            response.setEmployee(employee);
        } catch (EmployeeExistsException existsException) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("employee.exists"));
        } catch (SQLSyntaxErrorException syntaxException) {
            syntaxException.printStackTrace();
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = url, localPart = "findByIdRequest")
    @ResponsePayload
    public FindByIdResponse findByIdResponse(@RequestPayload FindByIdRequest findByIdRequest) {
        FindByIdResponse response = new FindByIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee employee = new Employee();

        employee.dao.daoemployee.entity.Employee daoEmployee = null;
        try {
            daoEmployee = employeeInterface.findById(findByIdRequest.getEmployeeId());
            BeanUtils.copyProperties(employee,daoEmployee);
            response.setEmployee(employee);
            serviceStatus.setMessage(resourceBundle.getString("fetch.success"));
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
        } catch (EmployeeException exception) {
            logger.warn(resourceBundle.getString("no.employee.id"));
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("no.employee.id"));
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }

    @PayloadRoot(namespace = url, localPart = "findByPincodeRequest")
    @ResponsePayload
    public FindByPincodeResponse findByPincodeResponse(@RequestPayload FindByPincodeRequest findByPincodeRequest) {
        FindByPincodeResponse response = new FindByPincodeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> employees = new ArrayList<>();
        List<employee.dao.daoemployee.entity.Employee> daoEmployee = null;

        try {
            daoEmployee = employeeInterface.findByPincode(findByPincodeRequest.getPincode());
        } catch (EmployeeException exception) {
            logger.warn(resourceBundle.getString("no.employees.pin"));
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("no.employees.pin"));
            response.setServiceStatus(serviceStatus);
        }

        Iterator<employee.dao.daoemployee.entity.Employee> iterator = daoEmployee.iterator();
        while (iterator.hasNext()) {
            Employee current = new Employee();
            BeanUtils.copyProperties(iterator.next(),current);
            employees.add(current);
        }
        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage(resourceBundle.getString("fetch.success"));
        response.setServiceStatus(serviceStatus);
//        response.getEmployee().addAll(employees);
        return response;
    }


}
