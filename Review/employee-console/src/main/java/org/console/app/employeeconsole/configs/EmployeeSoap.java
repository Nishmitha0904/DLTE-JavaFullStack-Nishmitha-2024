package org.console.app.employeeconsole.configs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.employee.dao.employeespringdao.interfaces.EmployeeInterface;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Endpoint
@ComponentScan("org.employee.dao.employeespringdao")
public class EmployeeSoap {
    private final String url="http://employee.services";

    @Autowired
    private EmployeeInterface employeeInterface;

    @PayloadRoot(namespace = url, localPart = "saveRequest")
    @ResponsePayload
    public SaveResponse saveEmployee(@RequestPayload SaveRequest request) {
        SaveResponse response = new SaveResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Employee actual = request.getEmployee();
        org.employee.dao.employeespringdao.entity.Employee employee = new org.employee.dao.employeespringdao.entity.Employee();
        BeanUtils.copyProperties(actual,employee);
        employee = employeeInterface.save(employee);
        response.setEmployee(actual);
        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage("Employee details saved");
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = url, localPart = "findAllEmployeesRequest")
    @ResponsePayload
    public FindAllEmployeesResponse findAll(@RequestPayload FindAllEmployeesRequest findAllEmployeesRequest) {
        FindAllEmployeesResponse response = new FindAllEmployeesResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> employees = new ArrayList<>();

        List<org.employee.dao.employeespringdao.entity.Employee> daoEmployee = employeeInterface.findAllEmployees();
        daoEmployee.forEach(each -> {
            Employee current = new Employee();
            BeanUtils.copyProperties(each, current);
            employees.add(current);
        });
        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage("Employee details displayed");
        response.setServiceStatus(serviceStatus);
        response.getEmployee().addAll(employees);
        return response;


    }
}
