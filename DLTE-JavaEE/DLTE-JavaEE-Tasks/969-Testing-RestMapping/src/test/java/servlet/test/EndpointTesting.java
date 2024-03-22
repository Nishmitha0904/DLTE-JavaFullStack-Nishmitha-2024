package servlet.test;

import org.database.Transaction;
import org.database.User;
import org.database.UserServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rest.endpoint.FindAllByDateAndUsername;
import rest.endpoint.FindAllByUsername;
import rest.endpoint.FindAllUsers;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class EndpointTesting {

    @Mock
    private UserServices services;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;

    @Before
    public void initiate() throws IOException {
        stringWriter=new StringWriter();
        printWriter=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    //findAll
    @Test
    public void testFindAll() throws ServletException, IOException {
        FindAllUsers findAllUsers = new FindAllUsers();
        findAllUsers.userServices=services;

        User user1 = new User("harshitha", "Har@123", "Mangalore","harshitha@gmail.com",7687567465L,60000.0);
        User user2 = new User("manisha","Mani@123","Udupi","manisha@gmail.com",7685746987L,40000.0);
        User user3 = new User("likhitha","Likhi@123","Mangalore","likhi@gmail.com",8756748367L,45000.0);

        List<User> users = Stream.of(user1, user2, user3).collect(Collectors.toList());
//        System.out.println(users.toString());

        when(services.callFindAll()).thenReturn(users);
        findAllUsers.doGet(request, response);

        verify(response).setContentType("application/json");
        verify(services).callFindAll();

        String expectedResult = "[{\"username\":\"harshitha\",\"password\":\"Har@123\",\"address\":\"Mangalore\",\"email\":\"harshitha@gmail.com\",\"contact\":7687567465,\"balance\":60000.0},{\"username\":\"manisha\",\"password\":\"Mani@123\",\"address\":\"Udupi\",\"email\":\"manisha@gmail.com\",\"contact\":7685746987,\"balance\":40000.0},{\"username\":\"likhitha\",\"password\":\"Likhi@123\",\"address\":\"Mangalore\",\"email\":\"likhi@gmail.com\",\"contact\":8756748367,\"balance\":45000.0}]";

        assertEquals(expectedResult, stringWriter.toString().trim());
    }

    @Test
    public void testFindAllByUsername() throws ServletException, IOException {
        FindAllByUsername findAllByUsername = new FindAllByUsername();
        findAllByUsername.userServices=services;

        User user1 = new User("harshitha", "Har@123", "Mangalore","harshitha@gmail.com",7687567465L,60000.0);
        User user2 = new User("manisha","Mani@123","Udupi","manisha@gmail.com",7685746987L,40000.0);
        User user3 = new User("likhitha","Likhi@123","Mangalore","likhi@gmail.com",8756748367L,45000.0);

        when(request.getParameter("username")).thenReturn("manisha");
        when(services.callFindById(anyString())).thenReturn(user2);

        findAllByUsername.doGet(request,response);
        verify(response).setContentType("application/json");
        verify(services).callFindById(anyString());

        assertEquals("{\"username\":\"manisha\",\"password\":\"Mani@123\",\"address\":\"Udupi\",\"email\":\"manisha@gmail.com\",\"contact\":7685746987,\"balance\":40000.0}", stringWriter.toString().trim());

    }

    @Test
    public void testFindAllByDateAndUsername() throws ServletException, IOException {
        FindAllByDateAndUsername findAllByDateAndUsername = new FindAllByDateAndUsername();
        findAllByDateAndUsername.userServices=services;

        Transaction transaction1 = new Transaction("pavana","deposit",5000.0,new Date(2024, 02,13));
        Transaction transaction2 = new Transaction("pavithra", "withdrawal", 20000.0, new Date(2024, 03, 15));
        Transaction transaction3 = new Transaction("pavana","withdrawal",8000.0,new Date(2024, 02,13));

        List<Transaction> expected = Stream.of(transaction1, transaction3).collect(Collectors.toList());


        when(request.getParameter("username")).thenReturn("pavana");
        when(request.getParameter("date")).thenReturn("02/13/2024");
        when(services.callFindByDateAndUsername(anyString(), anyString())).thenReturn(expected);
        findAllByDateAndUsername.doGet(request, response);

        verify(response).setContentType("application/json");
        verify(services).callFindByDateAndUsername(anyString(), anyString());

        assertEquals("[\n {\n \"username\": \"pavana\",\n \"type\": \"deposit\",\n \"amount\": 5000.0,\n \"date\": \"2024-02-13\"\n },\n {\n \"username\": \"pavana\",\n \"type\": \"withdrawal\",\n \"amount\": 8000.0,\n \"date\": \"2024-02-13\"\n }\n ]", stringWriter.toString().trim());
    }
}
