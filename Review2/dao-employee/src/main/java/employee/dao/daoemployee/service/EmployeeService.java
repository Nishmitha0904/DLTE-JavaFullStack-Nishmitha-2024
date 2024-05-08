package employee.dao.daoemployee.service;

import employee.dao.daoemployee.entity.Employee;
import employee.dao.daoemployee.exception.EmployeeException;
import employee.dao.daoemployee.exception.EmployeeExistsException;
import employee.dao.daoemployee.interfaces.EmployeeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

@Service
public class EmployeeService implements EmployeeInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @Override
    public Employee save(Employee employee) throws SQLSyntaxErrorException {
        try {
            String employeeQuery = "insert into employees values(?,?,?,?)";
            jdbcTemplate.update(employeeQuery, employee.getEmployeeID(), employee.getEmployeeName(), employee.getEmployeeEmail(), employee.getEmployeeMobile());

            String tempAddQuery = "insert into addresses(housename,area,city,state,pincode,address_type) values(?,?,?,?,?,'temporary')";
            jdbcTemplate.update(tempAddQuery, employee.getEmployeeTemporaryAddress().getHouseName(), employee.getEmployeeTemporaryAddress().getArea(),
                    employee.getEmployeeTemporaryAddress().getCity(), employee.getEmployeeTemporaryAddress().getState(), employee.getEmployeeTemporaryAddress().getPincode());

            String permAddQuery = "insert into addresses(housename,area,city,state,pincode,address_type) values(?,?,?,?,?,'permanent')";
            jdbcTemplate.update(permAddQuery, employee.getEmployeePermanentAddress().getHouseName(), employee.getEmployeePermanentAddress().getArea(),
                    employee.getEmployeePermanentAddress().getCity(), employee.getEmployeePermanentAddress().getState(), employee.getEmployeePermanentAddress().getPincode());

            logger.info(messageBundle.getString("add.success"));
            return employee;
        } catch (DataIntegrityViolationException exception) {
            logger.warn(messageBundle.getString("insert.fail"));
            throw new EmployeeExistsException(messageBundle.getString("insert.fail"));
        }
    }

    @Override
    public List<Employee> findAllEmployees() {
        String query = "select e.employee_id, e.employee_name, e.employee_email, e.employee_mobile, " +
                "ta.house_name as temp_house_name, ta.area as temp_area, ta.city as temp_city, ta.state as temp_state, ta.pincode as temp_pincode, " +
                "pa.house_name as perm_house_name, pa.area as perm_area, pa.city as perm_city, pa.state as perm_state, pa.pincode as perm_pincode " +
                "from employees e " +
                "inner join address ta on e.employee_id=ta.empid and ta.address_type = 'temporary' " +
                "inner join address pa on e.employee_id=pa.empid and pa.address_type = 'permanent'";
        List<Employee> employees = jdbcTemplate.query(query,new BeanPropertyRowMapper<>(Employee.class));
        if (employees.size()==0) {
            throw new EmployeeException(messageBundle.getString("no.employees"));
        }
        return employees;
    }

    @Override
    public List<Employee> findByPincode(Long pincode) {
        String query = "select e.employee_id, e.employee_name, e.employee_email, e.employee_mobile, " +
                "ta.house_name as temp_house_name, ta.area as temp_area, ta.city as temp_city, ta.state as temp_state, ta.pincode as temp_pincode, " +
                "pa.house_name as perm_house_name, pa.area as perm_area, pa.city as perm_city, pa.state as perm_state, pa.pincode as perm_pincode " +
                "from employees e " +
                "inner join address ta on e.employee_id=ta.empid and ta.address_type = 'temporary' " +
                "inner join address pa on e.employee_id=pa.empid and pa.address_type = 'permanent' " +
                "where ta.pincode=? or pa.pincode=?";
        List<Employee> employees = jdbcTemplate.query(query, new Object[]{pincode}, new BeanPropertyRowMapper<>(Employee.class));
        if (employees.size()==0) {
            throw new EmployeeException(messageBundle.getString("no.employees.pin"));
        }
        return employees;
    }

    @Override
    public Employee findById(Long id) {
        String query = "select e.employee_id, e.employee_name, e.employee_email, e.employee_mobile, " +
                "ta.house_name as temp_house_name, ta.area as temp_area, ta.city as temp_city, ta.state as temp_state, ta.pincode as temp_pincode, " +
                "pa.house_name as perm_house_name, pa.area as perm_area, pa.city as perm_city, pa.state as perm_state, pa.pincode as perm_pincode " +
                "from employees e " +
                "inner join address ta on e.employee_id=ta.empid and ta.address_type = 'temporary' " +
                "inner join address pa on e.employee_id=pa.empid and pa.address_type = 'permanent'" +
                "where e.employee_id=?";
        Employee employee = new Employee();
        employee = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));
        if (employee==null)
            throw new EmployeeException(messageBundle.getString("no.employee.id"));
        return employee;
    }
}
