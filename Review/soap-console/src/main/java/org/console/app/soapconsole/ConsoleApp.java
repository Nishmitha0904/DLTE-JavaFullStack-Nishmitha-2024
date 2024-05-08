package org.console.app.soapconsole;

import org.console.app.soapconsole.config.WebServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;
import java.util.ResourceBundle;
import services.employee.Employee;
import services.employee.ServiceStatus;

public class ConsoleApp {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static Logger logger = LoggerFactory.getLogger(ConsoleApp.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WebServiceConfig.class);
        context.refresh();

        WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);

        ConsoleApp app = new ConsoleApp();
        app.findAllEmployees(webServiceTemplate);

    }

    public void findAllEmployees(WebServiceTemplate webServiceTemplate) {
        services.employee.FindAllEmployeesRequest request = new services.employee.FindAllEmployeesRequest();
        services.employee.FindAllEmployeesResponse response = (services.employee.FindAllEmployeesResponse) webServiceTemplate.marshalSendAndReceive(request);
        List<Employee> employees = response.getEmployee();
        for (services.employee.Employee employee: employees) {
            displayDetails(employee);
        }
    }

    public static void displayDetails(Employee employee) {
            System.out.println("Employee ID: " + employee.getEmployeeId());
            System.out.println("Employee Name: " + employee.getEmployeeName());
            System.out.println("Employee Email: " + employee.getEmployeeEmail());
            System.out.println("Employee Mobile: " + employee.getEmployeeMobile());
            System.out.println("Employee Temporary Address: " + employee.getEmployeeTemporaryAddress());
            System.out.println("Employee Permanent Address: " + employee.getEmployeePermanentAddress());
            System.out.println();
//        }
    }

}
