package org.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddressDatabaseRepository implements AddressInterface {

    private Connection connection;
    private List<Address> employeeList = new ArrayList<>();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private Logger logger = LoggerFactory.getLogger("AddressDatabaseRepository.class");

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public AddressDatabaseRepository(Connection connection) {
        this.connection=connection;
    }

    public void save(Address address) {
        try {
            String query = "insert into address values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, address.getEmployee_val());
            preparedStatement.setString(2, address.getHouseName());
            preparedStatement.setString(3, address.getArea());
            preparedStatement.setString(4, address.getCity());
            preparedStatement.setString(5, address.getState());
            preparedStatement.setLong(6, address.getPincode());

            int result = preparedStatement.executeUpdate();
            if (result!=0) {
                logger.info(resourceBundle.getString("address.push.ok"));
                System.out.println(resourceBundle.getString("address.push.ok"));
            }
            else {
                logger.info(resourceBundle.getString("address.push.fail"));
                System.out.println(resourceBundle.getString("address.push.fail"));
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    @Override
    public List<Address> display() {
        return null;
    }
}
