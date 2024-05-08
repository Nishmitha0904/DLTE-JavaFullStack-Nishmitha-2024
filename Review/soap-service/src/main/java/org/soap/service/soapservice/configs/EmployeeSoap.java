package org.soap.service.soapservice.configs;

import org.employee.dao.employeespringdao.exception.EmployeeException;
import org.employee.dao.employeespringdao.exception.EmployeeExistsException;
import org.employee.dao.employeespringdao.interfaces.EmployeeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

@Endpoint
@ComponentScan("org.employee.dao.employeespringdao")
public class EmployeeSoap {
    private final String url="http://employee.services";
    Logger logger = LoggerFactory.getLogger(EmployeeSoap.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Autowired
    private EmployeeInterface employeeInterface;

    @PayloadRoot(namespace = url, localPart = "saveRequest")
    @ResponsePayload
    public SaveResponse saveEmployee(@RequestPayload SaveRequest saveRequest) {
        SaveResponse response = new SaveResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        String result = "";

        Employee actual = saveRequest.getEmployee();
        org.employee.dao.employeespringdao.entity.Employee daoEmployee = new org.employee.dao.employeespringdao.entity.Employee();
        org.employee.dao.employeespringdao.entity.Address temporaryAddress = new org.employee.dao.employeespringdao.entity.Address();
        org.employee.dao.employeespringdao.entity.Address permanentAddress = new org.employee.dao.employeespringdao.entity.Address();
        BeanUtils.copyProperties(actual,daoEmployee);
        BeanUtils.copyProperties(actual.getEmployeeTemporaryAddress(),temporaryAddress);
        BeanUtils.copyProperties(actual.getEmployeePermanentAddress(),permanentAddress);
        daoEmployee.setEmployeeTemporaryAddress(temporaryAddress);
        daoEmployee.setEmployeePermanentAddress(permanentAddress);
        try {
            result = employeeInterface.save(daoEmployee);
        } catch (EmployeeExistsException existsException) {
            response.setResult("Employee Details not saved");
            serviceStatus.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            serviceStatus.setMessage("Employee Id already exists");
            response.setServiceStatus(serviceStatus);
        } catch (SQLSyntaxErrorException syntaxException) {
            logger.error(resourceBundle.getString("internal.error"));
            serviceStatus.setMessage(resourceBundle.getString("internal.error"));
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setServiceStatus(serviceStatus);
        }
        if (result == "success") {
            BeanUtils.copyProperties(daoEmployee,actual);
            response.setResult("Employee saved successfully");
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage("Employee saved");
            response.setServiceStatus(serviceStatus);
        } else {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setResult("Employee was not Inserted");
            serviceStatus.setMessage("Employee not inserted");
            logger.info(result);
        }
        return response;
    }

    @PayloadRoot(namespace = url, localPart = "findAllEmployeesRequest")
    @ResponsePayload
    public FindAllEmployeesResponse findAll(@RequestPayload FindAllEmployeesRequest findAllEmployeesRequest) {
        FindAllEmployeesResponse response = new FindAllEmployeesResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> employees = new ArrayList<>();

        List<org.employee.dao.employeespringdao.entity.Employee> daoEmployee = null;
        try {
            daoEmployee = employeeInterface.findAllEmployees();
        } catch (EmployeeException exception) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("No employee details!");
        }

        Iterator<org.employee.dao.employeespringdao.entity.Employee> iterator = daoEmployee.iterator();
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

    @PayloadRoot(namespace = url, localPart = "findByIdRequest")
    @ResponsePayload
    public FindByIdResponse findByIdResponse(@RequestPayload FindByIdRequest findByIdRequest) {
        FindByIdResponse response = new FindByIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee employee = new Employee();

        org.employee.dao.employeespringdao.entity.Employee daoEmployee = null;
        try {
            daoEmployee = employeeInterface.findById(findByIdRequest.getEmployeeId());
            BeanUtils.copyProperties(employee,daoEmployee);
            response.setEmployee(employee);
            serviceStatus.setMessage("Employee details displayed");
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
        } catch (EmployeeException exception) {
            logger.warn("No employee found for given ID");
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("No employee found for given ID");
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
        List<org.employee.dao.employeespringdao.entity.Employee> daoEmployee = null;

        try {
            daoEmployee = employeeInterface.findByPincode(findByPincodeRequest.getPincode());
        } catch (EmployeeException exception) {
            logger.warn("No employee found for given ID");
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("No employee found for given pincode");
            response.setServiceStatus(serviceStatus);
        }

        Iterator<org.employee.dao.employeespringdao.entity.Employee> iterator = daoEmployee.iterator();
        while (iterator.hasNext()) {
            Employee current = new Employee();
            BeanUtils.copyProperties(iterator.next(),current);
            employees.add(current);
        }
        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage(resourceBundle.getString("fech.success"));
        response.setServiceStatus(serviceStatus);
//        response.getEmployee().addAll(employees);
        return response;
    }

}
