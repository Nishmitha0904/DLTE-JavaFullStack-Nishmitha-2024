package spring.explore.springbootjdbc;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SpringbootJdbcApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private MyBankService myBankService;

    @Test
    void testApprove() {

        Transaction transaction1 = new Transaction(8756474637L, new Date("20/03/2024"), 4000.0, "Peter", "Bills", "John");
        Transaction transaction2 = new Transaction(86745463746L, new Date("16/03/2024"), 8000.0, "Nancy", "Emergency", "Peter");
        lenient().when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        Transaction actual = myBankService.apiSave(transaction2);

//        assertEquals(transaction1.getTransactionBy(), actual.getTransactionBy());  //Fail
        assertTrue(transaction2.getRemarks()==actual.getRemarks());    // Pass

    }

    @Test
    void testFindBySender() {
        Transaction transaction1 = new Transaction(8756474637L, new Date("20/03/2024"), 4000.0, "Peter", "Bills", "John");
        Transaction transaction2 = new Transaction(86745463746L, new Date("16/03/2024"), 8000.0, "Nancy", "Emergency", "Peter");
        Transaction transaction3 = new Transaction(65748756478L, new Date("13/02/2023"), 20000.0, "John", "Emergency", "Peter");

        List<Transaction> expectedList = Stream.of(transaction2, transaction3).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(MyBankService.TransactionMapper.class))).thenReturn(expectedList);

        List<Transaction> actual = myBankService.apiFindBySender("Peter");

//        assertFalse(expectedList.size()==actual.size());      //Fail
        assertEquals(expectedList,actual);

    }

    @Test
    void testFindByReceiver() {
        Transaction transaction1 = new Transaction(8756474637L, new Date("20/03/2024"), 4000.0, "Peter", "Bills", "John");
        Transaction transaction2 = new Transaction(86745463746L, new Date("16/03/2024"), 8000.0, "Nancy", "Emergency", "Peter");
        Transaction transaction3 = new Transaction(65748756478L, new Date("13/02/2023"), 20000.0, "John", "Emergency", "Peter");
        Transaction transaction4 = new Transaction(8678594756L, new Date("22/01/2024"), 5000.0, "John", "Family", "Nancy");

        List<Transaction> expectedList = Stream.of(transaction3, transaction4).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(MyBankService.TransactionMapper.class))).thenReturn(expectedList);

        List<Transaction> actual = myBankService.apiFindByReceiver("John");

        assertSame(expectedList, actual);

    }

    @Test
    void testFindByAmount() {
        Transaction transaction1 = new Transaction(8756474637L, new Date("20/03/2024"), 4000.0, "Peter", "Bills", "John");
        Transaction transaction2 = new Transaction(86745463746L, new Date("16/03/2024"), 8000.0, "Nancy", "Emergency", "Peter");
        Transaction transaction4 = new Transaction(8678594756L, new Date("22/01/2024"), 4000.0, "John", "Family", "Nancy");

        List<Transaction> expectedList = Stream.of(transaction1,transaction4).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(MyBankService.TransactionMapper.class))).thenReturn(expectedList);

        List<Transaction> actual = myBankService.apiFindByAmount(4000.0);

//        assertFalse(expectedList.size()==actual.size());   //Fail

        assertEquals(expectedList, actual);

    }

}
