package mybank.dao.mybankdeposits;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.service.DepositService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.SQLSyntaxErrorException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@SpringBootTest
public class MyBankDepositsApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private DepositService depositService;

    @Test
    void testListByRoi() {
        Map<String, Object> returnedDeposits = new HashMap<>();
        returnedDeposits.put("id", BigDecimal.valueOf(1));
        returnedDeposits.put("name", "Deposit 1");
        returnedDeposits.put("out_roi", BigDecimal.valueOf(5.0));
        returnedDeposits.put("type", "Savings");
        returnedDeposits.put("description", "This is a sample deposit");

        // Mock jdbcTemplate.call() method
        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedDeposits);

        // Test with a valid ROI
        ResponseEntity<?> responseEntity = depositService.searchDepositsByRoi(5.0);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<DepositsAvailable> list = (List<DepositsAvailable>) responseEntity.getBody();
        assertEquals(1,list.get(0).getDepositId());
    }
    @Test
    void testListByRoiFail() {
        Map<String, Object> returnedDeposits = new HashMap<>();
        returnedDeposits.put("id", BigDecimal.valueOf(1));
        returnedDeposits.put("name", "Deposit 1");
        returnedDeposits.put("out_roi", BigDecimal.valueOf(5.0));
        returnedDeposits.put("type", "Savings");
        returnedDeposits.put("description", "This is a sample deposit");

        // Mock jdbcTemplate.call() method
        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedDeposits);

        // Test with a valid ROI
        ResponseEntity<?> responseEntity = depositService.searchDepositsByRoi(5.0);

        List<DepositsAvailable> actualList = (List<DepositsAvailable>) responseEntity.getBody();

//        assertNotEquals("Deposit 1",actualList.get(0).getDepositName());
//        assertSame(returnedDeposits,responseEntity);
//        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }

    @Test
    void testListAllDeposits() throws SQLSyntaxErrorException {
        List<DepositsAvailable> mockDepositList= new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(123L,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456L,"Flexi Saver",3.2,"Savings Account","A flexible savings account");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);

        List<DepositsAvailable> result =depositService.listAllDeposits();

        assertEquals(mockDepositList.get(0).getDepositId(),deposit1.getDepositId());
        assertEquals(mockDepositList.get(0).getDepositName(), deposit1.getDepositName());
        assertNotNull(mockDepositList);
    }

    @Test
    void listAllFailed() throws SQLSyntaxErrorException {
        List<DepositsAvailable> mockDepositList= new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(123L,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456L,"Flexi Saver",3.2,"Savings Account","A flexible savings account");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);

        List<DepositsAvailable> result =depositService.listAllDeposits();

        assertNotNull(mockDepositList);
//        assertEquals(mockDepositList.get(0).getDepositId(), deposit2.getDepositId());
    }


    @Test
    public void testListAllExc() {
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.emptyList());

//        List<DepositsAvailable> mockDepositList= new ArrayList<>();
//        DepositsAvailable deposit1 = new DepositsAvailable(123,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
//        DepositsAvailable deposit2 = new DepositsAvailable(456,"Flexi Saver",3.2,"Savings Account","A flexible savings account");
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);

        assertThrows(DepositException.class, ()->depositService.listAllDeposits());

    }



}
