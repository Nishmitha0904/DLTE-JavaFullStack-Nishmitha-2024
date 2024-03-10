package org.user.middleware;

import com.sun.org.slf4j.internal.LoggerFactory;
import org.user.entity.Account;
import org.user.remote.UserRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserDatabaseRepository implements UserRepository {

    private Connection connection;
    private List<Account> accountList = new ArrayList<>();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    //private Logger logger = LoggerFactory.getLogger(UserDatabaseRepository.class);
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserDatabaseRepository(Connection connection) {
        this.connection=connection;
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("user-logs.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

    }

    @Override
    public void createAccount(Account account) {
        try {
            String query = "insert into mybank_users values(?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(query);

            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3,account.getAddress());
            preparedStatement.setString(4,account.getEmail());
            preparedStatement.setLong(5,account.getContact());
            preparedStatement.setDouble(6,account.getBalance());

            int result = preparedStatement.executeUpdate();
            if (result!=0) {
                logger.log(Level.INFO, resourceBundle.getString("record.push.ok"));
                System.out.println(resourceBundle.getString("record.push.ok"));
            }
            else {
                logger.log(Level.INFO, resourceBundle.getString("record.push.fail"));
                System.out.println(resourceBundle.getString("record.push.fail"));
            }

        } catch (SQLException sqlException) {
            System.out.println(resourceBundle.getString("account.not.ok"));
        }
    }

    @Override
    public Account authenticate(String username) {
        return null;
    }
}
