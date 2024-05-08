package org.employee.dao.employeespringdao.validation;

import org.employee.dao.employeespringdao.entity.Employee;
import org.employee.dao.employeespringdao.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    static Logger logger = LoggerFactory.getLogger(Validation.class);
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("validation");

    public void validate(Employee employee) {
        if (!isValidName(employee.getEmployeeName())) {
            logger.warn(resourceBundle.getString("name.invalid"));
        }
        if (!isValidEmail(employee.getEmployeeEmail())) {
            logger.warn(resourceBundle.getString("email.invalid"));
        }
        if (!isValidMobileNumber(employee.getEmployeeMobile())) {
            logger.warn(resourceBundle.getString("mobile.invalid"));
        }
        if (!isValidPincode(employee.getEmployeeTemporaryAddress().getPincode())) {
            logger.warn(resourceBundle.getString("pincode.invalid"));
        }
        if (!isValidPincode(employee.getEmployeePermanentAddress().getPincode())) {
            logger.warn(resourceBundle.getString("pincode.invalid"));
        }
    }

    public Boolean isValidEmail(String email) {
        String emailExpression = "^[A-Za-z0-9+_.-]+@[a-zA-Z]{3,}+\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(emailExpression);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public Boolean isValidMobileNumber(Long mobileNumber) {
        String mobileString = Long.toString(mobileNumber);
        String mobileExpression = "\\d{10}";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(mobileString);
        return matcher.matches();
    }
    public Boolean isValidName(String name) {
        String nameExpression = "^[A-Za-z ]+$";
        Pattern pattern = Pattern.compile(nameExpression);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public Boolean isValidPincode(Long pincode) {
        String pincodeString = Long.toString(pincode);
        String pincodeExpression = "^[0-9]{6,6}$";
        Pattern pattern = Pattern.compile(pincodeExpression);
        Matcher matcher = pattern.matcher(pincodeString);
        return matcher.matches();
    }
}
