package org.database;

import java.sql.Date;
import java.util.List;

public interface UserRepository {
    void save(User user);
    User findById(String username);
    List<User> findAll();
    List<Transaction> findAllByUsername(String username);
    List<Transaction> findAllByDateAndUsername(String username, String transactionDate);
    List<Transaction> findAllTransactions();
}
