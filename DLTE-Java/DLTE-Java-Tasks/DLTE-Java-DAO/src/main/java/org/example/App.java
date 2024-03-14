package org.database;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StorageTarget storageTarget=new DatabaseTarget();

        UserServices userServices=new UserServices(storageTarget);

        User user=new User("demo@123","demo","1-89,R R nagara","demo@gmail.com",2345553L,4300.0);
        userServices.callSave(user);
       //System.out.println(userServices.callFindById("ra@123"));
    }
}
