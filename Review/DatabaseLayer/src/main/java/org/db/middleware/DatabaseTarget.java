package org.db.middleware;

import oracle.jdbc.driver.OracleDriver;
import org.db.exception.ConnectionException;
import org.db.remote.EmployeeInterface;
import org.db.remote.StorageTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

//public class DatabaseTarget implements StorageTarget {
public class DatabaseTarget {

    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    private static Connection connection;
    static Logger logger = LoggerFactory.getLogger("DatabaseTarget.class");

    public static Connection createConnection() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
        }
        catch (SQLException exception){
            logger.warn(resourceBundle.getString("db.connection.fail"));
            throw new ConnectionException();
        }
        return connection;
    }
//    public DatabaseTarget(){
//        try{
//            Driver driver=new OracleDriver();
//            DriverManager.registerDriver(driver);
//            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
//        }
//        catch (SQLException exception){
//            logger.warn(resourceBundle.getString("db.connection.fail"));
//            throw new ConnectionException();
//        }
//    }
//
//    @Override
//    public EmployeeInterface getEmployeeInterface() {
//        return new EmployeeImplementations(connection);
//    }
}
