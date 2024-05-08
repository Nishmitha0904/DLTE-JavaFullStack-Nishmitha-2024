package org.employee;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseTarget {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private Connection connection;

    public DatabaseTarget() {

        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EmployeeInterface getEmployeeInterface() {
        return new EmployeeDatabaseRepository(connection);
    }

//    public AddressInterface getAddressInterface() {
//        return new AddressDatabaseRepository(connection);
//    }
}

