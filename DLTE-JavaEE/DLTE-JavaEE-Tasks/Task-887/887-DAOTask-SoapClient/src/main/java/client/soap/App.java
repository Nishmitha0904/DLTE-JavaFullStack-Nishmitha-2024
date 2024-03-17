package client.soap;

import client.soap.web.Transaction;
import client.soap.web.User;
import client.soap.web.UserSoap;
import client.soap.web.UserSoapService;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserSoapService userSoapService = new UserSoapService();
        UserSoap soap = userSoapService.getUserSoapPort();

        /*
        //Create Account
        User user = new User();
        user.setUsername("manisha");
        user.setPassword("Mani@123");
        user.setAddress("Udupi");
        user.setEmail("manisha@gmail.com");
        user.setContact(8756234285L);
        user.setBalance(40000.0);
        soap.createAccount(user);
         */

        /*
        //Find By Username
        User user = soap.readByUsername("nishmitha");
        System.out.println(user.getUsername()+" "+user.getEmail()+" "+user.getAddress()+" "+user.getBalance());

         */

        /*
        //Find all transactions by username
        String username = "nishmitha";
        List<Transaction> transactions = soap.readAllByUsername(username).getTransactions();
        for (Transaction each: transactions) {
            System.out.println(each.getTransactionDoneBy()+" "+each.getTransactionType()+" "+each.getTransactionAmount());
        }

         */

    }
}
