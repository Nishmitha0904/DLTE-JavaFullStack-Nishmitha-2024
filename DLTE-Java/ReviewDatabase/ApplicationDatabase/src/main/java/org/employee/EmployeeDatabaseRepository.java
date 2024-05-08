package org.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDatabaseRepository implements EmployeeInterface{
    private Connection connection;
    private List<Employee> employeeList = new ArrayList<>();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private Logger logger = LoggerFactory.getLogger("EmployeeDatabaseRepository.class");

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public EmployeeDatabaseRepository(Connection connection) {
            this.connection=connection;
    }

    @Override
    public void save(Employee employee) {

        try {
            String query =  "insert into employees values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, employee.getEmployeeID());
            preparedStatement.setString(2, employee.getEmployeeName());
            preparedStatement.setString(3, employee.getEmployeeEmail());
            preparedStatement.setLong(4, employee.getEmployeeMobile());
            preparedStatement.setObject(5, employee.getEmployeeTemporaryAddress());
            preparedStatement.setObject(6,employee.getEmployeePermanentAddress());

            int result = preparedStatement.executeUpdate();
            if (result!=0) {
                logger.info(resourceBundle.getString("employee.push.ok"));
                System.out.println(resourceBundle.getString("employee.push.ok"));
            }
            else {
                logger.info(resourceBundle.getString("employee.push.fail"));
                System.out.println(resourceBundle.getString("employee.push.fail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Employee> display() {
        try {
            String query = "select * from employees";
            preparedStatement=connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            employeeList=new ArrayList<>();
            Employee employee = null;
            while (resultSet.next()) {
                employee = new Employee();
                employee.setEmployeeID(resultSet.getLong(1));
                employee.setEmployeeName(resultSet.getString(2));
                employee.setEmployeeEmail(resultSet.getString(3));
                employee.setEmployeeMobile(resultSet.getLong(4));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return employeeList;
    }

//    @Override
//    public List<Employee> findByPincode(Long aLong) {
//        return null;
//    }
}
