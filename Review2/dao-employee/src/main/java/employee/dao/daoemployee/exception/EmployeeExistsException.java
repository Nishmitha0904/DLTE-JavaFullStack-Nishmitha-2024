package org.employee.dao.employeespringdao.exception;

import java.util.ResourceBundle;

public class EmployeeExistsException extends RuntimeException {
    public EmployeeExistsException(String message) {
        super(message);
    }
}
