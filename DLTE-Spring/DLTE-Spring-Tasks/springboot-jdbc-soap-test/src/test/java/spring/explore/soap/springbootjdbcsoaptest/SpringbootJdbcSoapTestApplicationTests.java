package spring.explore.soap.springbootjdbcsoaptest;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.transaction.*;
import spring.explore.soap.springbootjdbcsoaptest.configs.SoapPhase;
import spring.explore.soap.springbootjdbcsoaptest.dao.Transaction;
import spring.explore.soap.springbootjdbcsoaptest.dao.TransactionService;

import javax.xml.datatype.XMLGregorianCalendar;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SpringbootJdbcSoapTestApplicationTests {

    @MockBean
    private TransactionService transactionService;
    @InjectMocks
    private SoapPhase soapPhase;

    @Test
    public void testNewTransaction() {
        Transaction transaction1 = new Transaction(64653788L, new Date(2024, 03, 28), 4000.0, "Nishmitha", "Emergency" ,"Harshitha");
        Transaction transaction2 = new Transaction(87347432L, new Date(2024, 02, 12), 20000.0, "Ghui", "Bills", "Nishmitha");

        when(transactionService.publishNewTransaction(any(Transaction.class))).thenReturn(transaction2);

        NewTransactionRequest request = new NewTransactionRequest();
//        services.transaction.Transaction transaction = new services.transaction.Transaction();
        services.transaction.Transaction transaction = new services.transaction.Transaction();
        transaction.setTransactionId(87347432L);
//        Instant.parse("2024-03-29T00:00:00Z")
        transaction.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024,03,29,0));
        transaction.setTransactionAmount(20000.0);
        transaction.setTransactionTo("Ghui");
        transaction.setRemarks("Bills");
        transaction.setTransactionBy("Nishmitha");
        request.setTransaction(transaction);

        NewTransactionResponse response = soapPhase.addNewTransaction(request);

//        assertEquals(4000.0, response.getTransaction().getTransactionAmount()); //Fail
        assertNotNull(response.getTransaction());  //Pass
        assertTrue(transaction2.getTransactionBy().equals(response.getTransaction().getTransactionBy()));  //Pass
//        assertNotEquals("SUCCESS", response.getServiceStatus().getStatus());       //Fail
    }

    @Test
    public void testFindBySender() {
        Transaction transaction1 = new Transaction(64653788L, new Date(2024, 03, 28), 4000.0, "Nishmitha", "Emergency" ,"Harshitha");
        Transaction transaction2 = new Transaction(87347432L, new Date(2024, 02, 12), 20000.0, "Ghui", "Bills", "Nishmitha");
        Transaction transaction3 = new Transaction(65748758L, new Date(2024, 03, 27), 5000.0, "Fred", "Bills", "Nishmitha");

        List<Transaction> expectedTransactions = Stream.of(transaction2, transaction3).collect(Collectors.toList());

        when(transactionService.findBySender(anyString())).thenReturn(expectedTransactions);

        FindBySenderRequest request = new FindBySenderRequest();
        request.setSender("Nishmitha");
        FindBySenderResponse response = soapPhase.findSender(request);

//        assertEquals(transaction2.getTransactionAmount(), response.getTransaction().get(1).getTransactionAmount());  //Fail
        assertTrue(transaction2.getTransactionId()==response.getTransaction().get(0).getTransactionId());   //Pass
    }

    @Test
    public void testFindByReceiver() {
        Transaction transaction1 = new Transaction(64653788L, new Date(2024, 03, 28), 4000.0, "Nishmitha", "Emergency" ,"Harshitha");
        Transaction transaction2 = new Transaction(87347432L, new Date(2024, 02, 12), 20000.0, "Ghui", "Bills", "Nishmitha");
        Transaction transaction3 = new Transaction(65748758L, new Date(2024, 03, 27), 5000.0, "Ghui", "Bills", "Nishmitha");

        List<Transaction> expectedTransactions = Stream.of(transaction2, transaction3).collect(Collectors.toList());

        when(transactionService.findByReceiver(anyString())).thenReturn(expectedTransactions);

        FindByReceiverRequest request = new FindByReceiverRequest();
        request.setReceiver("Ghui");
        FindByReceiverResponse response = soapPhase.findReceiver(request);

        assertTrue(transaction1.getTransactionId()!=response.getTransaction().get(0).getTransactionId());
        assertEquals(transaction2.getTransactionBy(), response.getTransaction().get(0).getTransactionBy());
//        assertFalse(transaction1.getTransactionAmount()!=response.getTransaction().get(1).getTransactionAmount());  //Fail
    }

    @Test
    public void testFindByAmount() {
        Transaction transaction1 = new Transaction(64653788L, new Date(2024, 03, 28), 4000.0, "Nishmitha", "Emergency" ,"Harshitha");
        Transaction transaction2 = new Transaction(87347432L, new Date(2024, 02, 12), 20000.0, "Ghui", "Bills", "Nishmitha");
        Transaction transaction3 = new Transaction(65748758L, new Date(2024, 03, 27), 5000.0, "Ghui", "Bills", "Nishmitha");

        List<Transaction> expectedTransactions = Stream.of(transaction1, transaction3).collect(Collectors.toList());

        when(transactionService.findByAmount(anyDouble(),anyDouble())).thenReturn(expectedTransactions);

        FindByAmountRequest request = new FindByAmountRequest();
        request.setMinAmount(1000);
        request.setMaxAmount(8000);
        FindByAmountResponse response = soapPhase.findAmount(request);

        assertEquals(transaction1.getTransactionAmount(), response.getTransaction().get(0).getTransactionAmount());
    }

    @Test
    public void testUpdateTransaction() {
        Transaction transaction1 = new Transaction(64653788L, new Date(2024, 03, 28), 4000.0, "Nishmitha", "Emergency" ,"Harshitha");
        Transaction transaction2 = new Transaction(87347432L, new Date(2024, 02, 12), 20000.0, "Ghui", "Bills", "Nishmitha");

        when(transactionService.updateRemarks(any(Transaction.class))).thenReturn(transaction2);

        UpdateRemarksRequest request = new UpdateRemarksRequest();
        services.transaction.Transaction transaction = new services.transaction.Transaction();
        transaction.setRemarks("Family");
        transaction.setTransactionId(87347432L);
        request.setTransaction(transaction);

        UpdateRemarksResponse response = soapPhase.updatingRemarks(request);

        assertEquals(transaction2.getRemarks(), response.getTransaction().getRemarks());
        assertEquals("Nishmitha has updated the remarks", response.getServiceStatus().getMessage());
//        assertNull(response.getServiceStatus().getStatus());
    }

    @Test
    public void testDeleteTransaction() {
        when(transactionService.deleteTransactionBetweenDates(any(),any())).thenReturn("Transaction details deleted");

        DeleteTransactionRequest request = new DeleteTransactionRequest();

        XMLGregorianCalendar startDate = XMLGregorianCalendarImpl.createDateTime(2024, 02, 12,1,20,30,0,0);
        XMLGregorianCalendar endDate = XMLGregorianCalendarImpl.createDateTime(2024, 02, 14,1,20,30,0,0);

        request.setStartDate(startDate);
        request.setEndDate(endDate);

        DeleteTransactionResponse response = soapPhase.deletingTransaction(request);

        assertEquals("Transaction details deleted", response.getServiceStatus().getMessage());
        assertNotNull(response.getServiceStatus().getStatus());
    }
}
