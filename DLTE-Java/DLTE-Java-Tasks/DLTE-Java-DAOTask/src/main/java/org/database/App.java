package org.database;

import java.sql.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StorageTarget storageTarget = new DatabaseTarget();
        UserServices userServices = new UserServices(storageTarget);

        System.out.println(userServices.callFindByDateAndUsername("nishmitha", Date.valueOf("2024-03-12")));
    }
}
