package org.db.exception;

import java.util.ResourceBundle;

public class EmployeeExistsException extends RuntimeException {
    public EmployeeExistsException() {
        super(ResourceBundle.getBundle("exception").getString("employee.exists"));
    }
}
