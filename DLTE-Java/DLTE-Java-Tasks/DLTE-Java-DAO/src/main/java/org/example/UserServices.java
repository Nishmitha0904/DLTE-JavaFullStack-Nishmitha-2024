package org.example;

public class UserServices {
    UserRepository userRepository;
    public UserServices(StorageTarget storageTarget){
        userRepository=storageTarget.getUserRepository();
    }


    public void callSave(User user){
        try{
            userRepository.save(user);
        }catch (UserException userException){
            throw userException;
        }
    }
    public User callFindById(String username){
        try{
            return userRepository.findById(username);
        }
        catch (UserException userException){
            throw userException;
        }
    }
}
