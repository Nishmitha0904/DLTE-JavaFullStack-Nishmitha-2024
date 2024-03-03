package database.dlte;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreatingTable {
    public static void main(String[] args) {
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(resourceBundle.getString("db.url"));
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }
}
