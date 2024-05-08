package org.db.validation;

import org.db.entity.Employee;
import org.db.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    static Logger logger = LoggerFactory.getLogger("Validation.class");

    public void employeeValidation(Employee employee) throws ValidationException {

        if (!isValidEmail(employee.getEmployeeEmail())){
            logger.warn("Invalid Email");
            throw new ValidationException("validate-1");
        }

        if (!isValidMobileNumber(employee.getEmployeeMobile())) {
            logger.warn("Invalid mobile number");
            throw new ValidationException("validate-2");
        }
    }
    public Boolean isValidEmail(String borrowerEmail) {
        String emailExpression = "^[A-Za-z0-9+_.-]+@[a-zA-Z]{3,}+\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(emailExpression);
        Matcher matcher = pattern.matcher(borrowerEmail);
        return matcher.matches();
    }
    public Boolean isValidMobileNumber(Long mobileNumber) {
        String mobileString = Long.toString(mobileNumber);
        String mobileExpression = "\\d{10}";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(mobileString);
        return matcher.matches();
    }

}
