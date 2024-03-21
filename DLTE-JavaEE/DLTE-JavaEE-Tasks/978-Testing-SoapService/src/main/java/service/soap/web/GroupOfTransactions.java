package service.soap.web;

import org.database.Transaction;
import org.database.User;

import java.util.List;

public class GroupOfTransactions {

    List<Transaction> transactions;

    public GroupOfTransactions() {
    }

    public GroupOfTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "GroupOfTransactions{" +
                "transactions=" + transactions +
                '}';
    }
    //    List<User> users;
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//    public GroupOfUsers() {
//    }
//
//    public GroupOfUsers(List<User> users) {
//        this.users = users;
//    }
//
//    @Override
//    public String toString() {
//        return "GroupOfUsers{" +
//                "users=" + users +
//                '}';
//    }
}
