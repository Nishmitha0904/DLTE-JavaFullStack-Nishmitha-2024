package service.soap;

import static org.junit.Assert.assertTrue;

import org.database.Transaction;
import org.database.User;
import org.database.UserServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.soap.web.GroupOfTransactions;
import service.soap.web.GroupOfUsers;
import service.soap.web.UserSoap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    @Mock
    UserServices userServices;

    private UserSoap soapService;

    @Before
    public void settingUp() {
        soapService=new UserSoap();
        soapService.services=userServices;
    }

    //findAll
    @Test
    public void testFindAll() {

        Transaction transaction1 = new Transaction("harshitha","deposit",50000.0,new Date(2024, 02,13));
        Transaction transaction2 = new Transaction("manisha", "withdrawal", 3000.0, new Date(2024, 03, 15));
        List<Transaction> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());

        when(userServices.callFindAllTransactions()).thenReturn(expectedList);

        GroupOfTransactions groupOfTransactions = soapService.readAllTransactions();

//        assertNull(groupOfTransactions); //Failed test case

        assertNotNull(groupOfTransactions); //Pass

        assertSame("harshitha", groupOfTransactions.getTransactions().get(0).getTransactionDoneBy()); //Pass

    }

    //findAllByUsername
    @Test
    public void testFindByUsername() {

        String user="manisha";
        Transaction transaction1 = new Transaction("harshitha","deposit",50000.0,new Date(2024, 02,13));
        Transaction transaction2 = new Transaction("manisha", "withdrawal", 3000.0, new Date(2024, 03, 15));
        List<Transaction> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());

        when(userServices.callFindByUsername(user)).thenReturn(expectedList);

        GroupOfTransactions groupOfTransactions = soapService.readAllByUsername(user);

//        assertEquals("manisha", groupOfTransactions.getTransactions().get(0).getTransactionDoneBy()); //Fail(Comparision Failure)

        assertEquals("manisha", groupOfTransactions.getTransactions().get(1).getTransactionDoneBy());  //Pass

//        assertTrue(groupOfTransactions.getTransactions().get(1).equals(expectedList));  //AssertionError

        assertTrue(groupOfTransactions.getTransactions().equals(expectedList));

    }

    //Creating new account service
    @Test
    public void testCreateAccount() {
        User user1 = new User("likhitha", "Likhi@123", "Mangalore", "likhi@gmail.com", 8756748367L, 45000.0);
        User user2 = new User("pavana", "Pav@123", "Mangalore", "pavana@gmail.com",8767584758L, 40000.0);

        doNothing().when(userServices).callSave(user1);
        soapService.createAccount(user1);
        verify(userServices, times(1)).callSave(user1);  //Pass
//        verify(userServices, times(1)).callSave(user2);  //Fail
    }
}
