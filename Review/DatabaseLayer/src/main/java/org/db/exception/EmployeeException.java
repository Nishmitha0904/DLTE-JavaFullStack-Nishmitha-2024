package org.db.exception;

import java.util.ResourceBundle;

public class EmployeeException extends RuntimeException {
    public EmployeeException() {
        super(ResourceBundle.getBundle("exception").getString("employee.not.found"));
    }
}
