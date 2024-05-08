package org.db.middleware;

import org.db.entity.Address;
import org.db.entity.Employee;
import org.db.exception.EmployeeException;
import org.db.exception.EmployeeExistsException;
import org.db.remote.EmployeeInterface;
import org.db.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeImplementations implements EmployeeInterface {

    private Connection connection;
    private List<Employee> employeeList = new ArrayList<>();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    ResourceBundle exceptionResourceBundle = ResourceBundle.getBundle("exception");
    private Logger logger = LoggerFactory.getLogger("EmployeeDatabaseRepository.class");
    static Validation validation = new Validation();

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

//    EmployeeInterface employeeInterface;

//    public EmployeeImplementations(Connection connection) {
//        this.connection=connection;
//    }
    public EmployeeImplementations() {
        connection = DatabaseTarget.createConnection();
    }

    @Override
    public void save(Employee employee) throws SQLException {
        try {
            String employeeQuery = "insert into employees values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(employeeQuery);

            preparedStatement.setLong(1, employee.getEmployeeID());
            preparedStatement.setString(2, employee.getEmployeeName());
            preparedStatement.setString(3, employee.getEmployeeEmail());
            preparedStatement.setLong(4, employee.getEmployeeMobile());
            int employeeResult = preparedStatement.executeUpdate();
            if (employeeResult==0) {
                logger.info(resourceBundle.getString("employee.push.fail"));
                System.out.println(resourceBundle.getString("employee.push.fail"));
            }
            logger.info(resourceBundle.getString("employee.push.ok"));


            String addressQuery = "insert into address values(?,?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(addressQuery);
            preparedStatement.setLong(1,employee.getEmployeeID());
            preparedStatement.setString(2,"temporary");
            preparedStatement.setString(3, employee.getEmployeeTemporaryAddress().getHouseName());
            preparedStatement.setString(4,employee.getEmployeeTemporaryAddress().getArea());
            preparedStatement.setString(5, employee.getEmployeeTemporaryAddress().getCity());
            preparedStatement.setString(6,employee.getEmployeeTemporaryAddress().getState());
            preparedStatement.setLong(7,employee.getEmployeeTemporaryAddress().getPincode());
            int temporaryResult = preparedStatement.executeUpdate();

//            String permanentAddress = "insert into permanent_address values(?,?,?,?,?,?)";
//            preparedStatement=connection.prepareStatement(permanentAddress);
//            preparedStatement.setLong(1, employee.getEmployeeID());
            preparedStatement.setString(2,"permanent");
            preparedStatement.setString(3, employee.getEmployeePermanentAddress().getHouseName());
            preparedStatement.setString(4,employee.getEmployeePermanentAddress().getArea());
            preparedStatement.setString(5, employee.getEmployeePermanentAddress().getCity());
            preparedStatement.setString(6,employee.getEmployeePermanentAddress().getState());
            preparedStatement.setLong(7,employee.getEmployeePermanentAddress().getPincode());
            int permanentResult = preparedStatement.executeUpdate();


            if (temporaryResult==0 || permanentResult==0) {
                logger.info(resourceBundle.getString("address.push.fail"));
                System.out.println(resourceBundle.getString("address.push.fail"));
            } else {
                logger.info(resourceBundle.getString("address.push.ok"));
            }

//            resultSet.close();
//            preparedStatement.close();
//            connection.close();

        } catch (SQLIntegrityConstraintViolationException integrityViolation) {
            logger.warn(exceptionResourceBundle.getString("employee.exists"));
            throw new EmployeeExistsException();
        }
//        catch (SQLException e) {
//            logger.warn("SQLException");
//        }
//        finally {
//            try {
//                resultSet.close();
//                preparedStatement.close();
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }


    //Displaying all employee details
    @Override
    public List<Employee> display() throws SQLException {
//        try {

//            String query = "select * from employees " +
//                    "inner join temporary_address on employees.employee_id=temporary_address.empid " +
//                    "inner join permanent_address on employees.employee_id=permanent_address.empid";
            String query = "select e.employee_id, e.employee_name, e.employee_email, e.employee_mobile, " +
                    "ta.house_name as temp_house_name, ta.area as temp_area, ta.city as temp_city, ta.state as temp_state, ta.pincode as temp_pincode, " +
                    "pa.house_name as perm_house_name, pa.area as perm_area, pa.city as perm_city, pa.state as perm_state, pa.pincode as perm_pincode " +
                    "from employees e " +
                    "inner join address ta on e.employee_id=ta.empid and ta.address_type = 'temporary' " +
                    "inner join address pa on e.employee_id=pa.empid and pa.address_type = 'permanent'";
            preparedStatement=connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getLong(1));
                employee.setEmployeeName(resultSet.getString(2));
                employee.setEmployeeEmail(resultSet.getString(3));
                employee.setEmployeeMobile(resultSet.getLong(4));

                Address temporaryAddress = new Address();
                temporaryAddress.setHouseName(resultSet.getString(5));
                temporaryAddress.setArea(resultSet.getString(6));
                temporaryAddress.setCity(resultSet.getString(7));
                temporaryAddress.setState(resultSet.getString(8));
                temporaryAddress.setPincode(resultSet.getLong(9));
                employee.setEmployeeTemporaryAddress(temporaryAddress);

                Address permanentAddress = new Address();
                permanentAddress.setHouseName(resultSet.getString(10));
                permanentAddress.setArea(resultSet.getString(11));
                permanentAddress.setCity(resultSet.getString(12));
                permanentAddress.setState(resultSet.getString(13));
                permanentAddress.setPincode(resultSet.getLong(14));
                employee.setEmployeePermanentAddress(permanentAddress);

                employeeList.add(employee);
            }
            if (employeeList.size()==0)
                throw new EmployeeException();
            connection.close();

//        } catch (SQLException e) {
//            logger.warn("SQLException");
//        }
        return employeeList;
    }

    //Find employees having particular pincode
    @Override
    public List<Employee> displayByPincode(Long pincode) throws SQLException{
//        try {
//            String query = "select * from employees " +
//                    "inner join temporary_address on employees.employee_id=temporary_address.empid " +
//                    "inner join permanent_address on employees.employee_id=permanent_address.empid " +
//                    "where temporary_address.pincode=?  or permanent_address.pincode=?";
            String query = "select e.employee_id, e.employee_name, e.employee_email, e.employee_mobile, " +
            "ta.house_name as temp_house_name, ta.area as temp_area, ta.city as temp_city, ta.state as temp_state, ta.pincode as temp_pincode, " +
                    "pa.house_name as perm_house_name, pa.area as perm_area, pa.city as perm_city, pa.state as perm_state, pa.pincode as perm_pincode " +
                    "from employees e " +
                    "inner join address ta on e.employee_id=ta.empid and ta.address_type = 'temporary' " +
                    "inner join address pa on e.employee_id=pa.empid and pa.address_type = 'permanent' " +
                    "where ta.pincode=? or pa.pincode=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setLong(1, pincode);
            preparedStatement.setLong(2,pincode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getLong(1));
                employee.setEmployeeName(resultSet.getString(2));
                employee.setEmployeeEmail(resultSet.getString(3));
                employee.setEmployeeMobile(resultSet.getLong(4));

                Address temporaryAddress = new Address();
                temporaryAddress.setHouseName(resultSet.getString(5));
                temporaryAddress.setArea(resultSet.getString(6));
                temporaryAddress.setCity(resultSet.getString(7));
                temporaryAddress.setState(resultSet.getString(8));
                temporaryAddress.setPincode(resultSet.getLong(9));
                employee.setEmployeeTemporaryAddress(temporaryAddress);

                Address permanentAddress = new Address();
                permanentAddress.setHouseName(resultSet.getString(10));
                permanentAddress.setArea(resultSet.getString(11));
                permanentAddress.setCity(resultSet.getString(12));
                permanentAddress.setState(resultSet.getString(13));
                permanentAddress.setPincode(resultSet.getLong(14));
                employee.setEmployeePermanentAddress(permanentAddress);

                employeeList.add(employee);
            }
            if (employeeList.size()==0)
                throw new EmployeeException();
            connection.close();

//        } catch (SQLException e) {
//            logger.warn("SQL Exception");
//        }
        return employeeList;
    }

    @Override
    public Employee findById(Long empid) {
        try {
//            String query = "select * from employees " +
//                    "inner join temporary_address on employees.employee_id=temporary_address.empid " +
//                    "inner join permanent_address on employees.employee_id=permanent_address.empid " +
//                    "where employees.employee_id=?";
            String query = "select e.employee_id, e.employee_name, e.employee_email, e.employee_mobile, " +
                    "ta.house_name as temp_house_name, ta.area as temp_area, ta.city as temp_city, ta.state as temp_state, ta.pincode as temp_pincode, " +
                    "pa.house_name as perm_house_name, pa.area as perm_area, pa.city as perm_city, pa.state as perm_state, pa.pincode as perm_pincode " +
                    "from employees e " +
                    "inner join address ta on e.employee_id=ta.empid and ta.address_type = 'temporary' " +
                    "inner join address pa on e.employee_id=pa.empid and pa.address_type = 'permanent'" +
                    "where e.employee_id=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setLong(1, empid);
            resultSet = preparedStatement.executeQuery();

            Employee employee = new Employee();
            if (resultSet.next()) {

                employee.setEmployeeID(resultSet.getLong(1));
                employee.setEmployeeName(resultSet.getString(2));
                employee.setEmployeeEmail(resultSet.getString(3));
                employee.setEmployeeMobile(resultSet.getLong(4));

                Address temporaryAddress = new Address();
                temporaryAddress.setHouseName(resultSet.getString(5));
                temporaryAddress.setArea(resultSet.getString(6));
                temporaryAddress.setCity(resultSet.getString(7));
                temporaryAddress.setState(resultSet.getString(8));
                temporaryAddress.setPincode(resultSet.getLong(9));
                employee.setEmployeeTemporaryAddress(temporaryAddress);

                Address permanentAddress = new Address();
                permanentAddress.setHouseName(resultSet.getString(10));
                permanentAddress.setArea(resultSet.getString(11));
                permanentAddress.setCity(resultSet.getString(12));
                permanentAddress.setState(resultSet.getString(13));
                permanentAddress.setPincode(resultSet.getLong(14));
                employee.setEmployeePermanentAddress(permanentAddress);

                return employee;
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
