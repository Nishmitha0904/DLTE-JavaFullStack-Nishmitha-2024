package client.soap;

import client.soap.web.Transaction;
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
        String username = "nishmitha";
        List<Transaction> transactions = soap.readAllByUsername(username).getTransactions();
        for (Transaction each: transactions) {
            System.out.println(each.getTransactionDoneBy()+" "+each.getTransactionType()+" "+each.getTransactionAmount());
        }
    }
}
