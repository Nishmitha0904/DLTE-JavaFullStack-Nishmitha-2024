package spring.explore.soap.springbootjdbcsoaptest;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import services.transaction.*;
import spring.explore.soap.springbootjdbcsoaptest.configs.SoapPhase;
import spring.explore.soap.springbootjdbcsoaptest.dao.Transaction;
import spring.explore.soap.springbootjdbcsoaptest.dao.TransactionService;
import sun.util.calendar.LocalGregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndpointTest {
    @MockBean
    private TransactionService transactionService;
    @InjectMocks
    SoapPhase soapPhase;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testAdd() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(73636576L);
        transaction.setTransactionDate(new Date(2024,03, 30));
        transaction.setTransactionAmount(5000.0);
        transaction.setTransactionTo("Nishmitha");
        transaction.setRemarks("Emergency");
        transaction.setTransactionBy("Sudhakar");
        when(transactionService.publishNewTransaction(any(Transaction.class))).thenReturn(transaction);
        NewTransactionRequest request = new NewTransactionRequest();
        services.transaction.Transaction transaction1 = new services.transaction.Transaction();
        transaction1.setTransactionId(73636576L);
        transaction1.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024,03,30,0));
        transaction1.setTransactionAmount(5000.0);
        transaction1.setTransactionTo("Nishmitha");
        transaction1.setRemarks("Emergency");
        transaction1.setTransactionBy("Sudhakar");
        request.setTransaction(transaction1);
        NewTransactionResponse response = soapPhase.addNewTransaction(request);

        assertEquals(response.getServiceStatus().getStatus(), "SUCCESS");
    }

   @Test
    public void testFindBySender() {
       Transaction transaction1 = new Transaction(73636576L, new Date(2024,03, 30), 5000.0, "Nishmitha", "Emergency", "Sudhakar");
       List<Transaction> transactions = Stream.of(transaction1).collect(Collectors.toList());
       when(transactionService.findBySender("Sudhakar")).thenReturn(transactions);
       FindBySenderRequest request = new FindBySenderRequest();
       request.setSender("Sudhakar");
       FindBySenderResponse response = soapPhase.findSender(request);

       assertEquals(transaction1.getTransactionTo(), response.getTransaction().get(0).getTransactionTo());
       assertTrue(response.getServiceStatus().getStatus().equals("SUCCESS"));
   }

    @Test
    public void testFindByReceiver() {
        Transaction transaction1 = new Transaction(73636576L, new Date(2024,03, 30), 5000.0, "Nishmitha", "Emergency", "Sudhakar");
        List<Transaction> transactions = Stream.of(transaction1).collect(Collectors.toList());
        when(transactionService.findByReceiver("Nishmitha")).thenReturn(transactions);
        FindByReceiverRequest request = new FindByReceiverRequest();
        request.setReceiver("Nishmitha");
        FindByReceiverResponse response = soapPhase.findReceiver(request);

        assertEquals(transaction1.getTransactionTo(), response.getTransaction().get(0).getTransactionTo());
        assertTrue(response.getServiceStatus().getStatus().equals("SUCCESS"));
    }

    @Test
    public void testFindByAmount() {
        Transaction transaction1 = new Transaction(73636576L, new Date(2024,03, 30), 5000.0, "Nishmitha", "Emergency", "Sudhakar");
        List<Transaction> transactions = Stream.of(transaction1).collect(Collectors.toList());
        when(transactionService.findByAmount(1000.0, 7000.0)).thenReturn(transactions);
        FindByAmountRequest request = new FindByAmountRequest();
        request.setMinAmount(1000);
        request.setMaxAmount(7000);
        FindByAmountResponse response = soapPhase.findAmount(request);

        assertTrue(response.getServiceStatus().getStatus().equals("SUCCESS"));

        assertEquals(transaction1.getTransactionAmount(), response.getTransaction().get(0).getTransactionAmount());

    }

    @Test
    public void testUpdate() {
        Transaction transaction = new Transaction();

        transaction.setTransactionId(73636576L);
        transaction.setTransactionDate(new Date(2024,03, 30));
        transaction.setTransactionAmount(5000.0);
        transaction.setTransactionTo("Nishmitha");
        transaction.setRemarks("Emergency");
        transaction.setTransactionBy("Sudhakar");
        when(transactionService.updateRemarks(any(Transaction.class))).thenReturn(transaction);
        UpdateRemarksRequest request = new UpdateRemarksRequest();
        services.transaction.Transaction transaction1 = new services.transaction.Transaction();
        transaction1.setTransactionId(73636576L);
        transaction1.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024,03,30,0));
        transaction1.setTransactionAmount(5000.0);
        transaction1.setTransactionTo("Nishmitha");
        transaction1.setRemarks("Emergency");
        transaction1.setTransactionBy("Sudhakar");
        request.setTransaction(transaction1);
        UpdateRemarksResponse response = soapPhase.updatingRemarks(request);

        assertEquals(response.getServiceStatus().getStatus(), "SUCCESS");
    }

    @Test
    public void testDelete() {


        Date startDate = new Date(2024,03,27);
        Date endDate = new Date(2024, 03, 31);

        when(transactionService.deleteTransactionBetweenDates(startDate,endDate)).thenReturn("Transaction details deleted");

        DeleteTransactionRequest request = new DeleteTransactionRequest();

        XMLGregorianCalendar startDate1 = XMLGregorianCalendarImpl.createDateTime(2024, 02, 12,1,20,30,0,0);
        XMLGregorianCalendar endDate1 = XMLGregorianCalendarImpl.createDateTime(2024, 02, 14,1,20,30,0,0);
        request.setStartDate(startDate1);
        request.setEndDate(endDate1);

        DeleteTransactionResponse response = soapPhase.deletingTransaction(request);

        assertNotNull(response.getServiceStatus().getStatus());

    }

}
