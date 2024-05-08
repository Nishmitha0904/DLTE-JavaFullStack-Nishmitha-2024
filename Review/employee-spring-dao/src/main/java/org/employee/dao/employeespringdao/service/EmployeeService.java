package org.employee.dao.employeespringdao.service;

import org.employee.dao.employeespringdao.entity.Address;
import org.employee.dao.employeespringdao.entity.Employee;
import org.employee.dao.employeespringdao.exception.EmployeeException;
import org.employee.dao.employeespringdao.exception.EmployeeExistsException;
import org.employee.dao.employeespringdao.interfaces.EmployeeInterface;
import org.employee.dao.employeespringdao.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static Validation validation = new Validation();
    static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Override
    public String save(Employee employee) throws SQLSyntaxErrorException {
        Address tempAdd = employee.getEmployeeTemporaryAddress();
        Address permAdd = employee.getEmployeePermanentAddress();
        validation.validate(employee);
        try {
            int employeeAck = jdbcTemplate.update("insert into employees values(?,?,?,?)",
                    new Object[]{employee.getEmployeeID(),employee.getEmployeeName(),employee.getEmployeeEmail(),employee.getEmployeeMobile()});
            if (employeeAck==0) {
                logger.warn("Not inserted");
            }
            int tempAddAck = jdbcTemplate.update("insert into addresses(housename,area,city,state,pincode,address_type) values(?,?,?,?,?,?)",
                    new Object[]{tempAdd.getHouseName(),tempAdd.getArea(),tempAdd.getCity(),tempAdd.getState(),tempAdd.getPincode(),"temporary"});
            if (tempAddAck==0) {
                logger.warn("Not inserted");
            }
            int permAddAck = jdbcTemplate.update("insert into addresses(housename,area,city,state,pincode,address_type) values(?,?,?,?,?,?)",
                    new Object[]{permAdd.getHouseName(),permAdd.getArea(),permAdd.getCity(),permAdd.getState(), permAdd.getPincode()});
            if (permAddAck==0) {
                logger.warn("Not inserted");
            }
            return "success";
        } catch (DataIntegrityViolationException integrityException) {
            logger.warn("Employee Id already exists");
            throw new EmployeeExistsException("Employee Id already exists");
        } catch (DataAccessException sqlException) {
            throw new SQLSyntaxErrorException();
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
            throw new EmployeeException("No employee details");
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
            throw new EmployeeException("No employee details");
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
            throw new EmployeeException("No employee found");
        return employee;

    }
}
