package service.soap.web;

import org.database.User;

import java.util.ArrayList;
import java.util.List;

public class GroupOfUsers {

    List<User> users = new ArrayList<>();

    public GroupOfUsers(List<User> users) {
        this.users = users;
    }

    public GroupOfUsers() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "GroupOfUsers{" +
                "users=" + users +
                '}';
    }
}
