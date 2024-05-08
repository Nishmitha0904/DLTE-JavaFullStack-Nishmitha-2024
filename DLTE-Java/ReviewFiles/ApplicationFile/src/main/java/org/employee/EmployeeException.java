package org.employee;

import java.util.ResourceBundle;

public class EmployeeException extends RuntimeException {

    public EmployeeException()  {
        super(ResourceBundle.getBundle("application").getString("exception.employee"));
    }
}
