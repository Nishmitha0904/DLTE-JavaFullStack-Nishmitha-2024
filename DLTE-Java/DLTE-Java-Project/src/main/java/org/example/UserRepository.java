package org.example;
import org.example.User;
public interface UserRepository {
    void save(User user);
    User findById(String username);

//    //findByname method
//    User findByName(String username);
}
